package com.hb.PICOM_hibernate.initialisation;

import com.github.javafaker.*;
import com.github.javafaker.Country;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.hb.PICOM_hibernate.buisness.*;
import com.hb.PICOM_hibernate.buisness.Company;
import com.hb.PICOM_hibernate.dao.*;
import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor
public class AddFakeData implements CommandLineRunner {
    //generate all DAO
    private final AdvertismentDao adDao;
    private final CityDao cityDao;
    private final CompanyDao companyDao;
    private final CountryDao countryDao;
    private final InvoiceDao invoiceDao;
    private final PaymentMethodDao payMDao;
    private final TimeSlotDao timeSDao;
    private final TimeSlotListDao tslDao;
    private final RoleDao roleDao;
    private final StopDao stopDao;
    private final UserDao userDao;
    private final ZoneDao zoneDao;


    private List<com.hb.PICOM_hibernate.buisness.Country> countries;
    private List<City> cities;
    private List<Company>  companies;
    private List<User> users;
    private List<Advertisment> ads;

    //for random data
    private static Random random = new Random();
    private static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"), new RandomService());
    private static Faker faker = new Faker(new Locale("fr-FR"));


    /*METHOD RUN START*************************************************************/
    @Override
    public void run(String... args) throws Exception {
        addCountries();
        addCities();
        addCompanies();
        addUsers();
        addRoles();
        addZones();
        addStops();
        addTimeSlots();
        addAds();
        addTimeSlotsLists();
        addPaymentMethods();
        addInvoices();
    }
    /*METHOD RUN END*************************************************************/

    /*METHODS TO FILL DB START*************************************************************/

    private void addCountries(){
        //only if country table is empty
        if(countryDao.count() == 0){

            //Create 30 new country
            for(Integer i=0; i<10; i++){

                //faker country object
                Country country= faker.country();
                //create picom country object form faker data
                com.hb.PICOM_hibernate.buisness.Country newCountry = new com.hb.PICOM_hibernate.buisness.Country(country.countryCode3(),country.name());

                //save in db
                countryDao.save(newCountry);
            }
        }
    }

    private void addCities(){
        //only if city table is empty
        if(cityDao.count()==0){

            /*get all countries*/
            this.countries = countryDao.findAll();

            //Create 30 cities
            for(Integer i=0; i<10; i++){

                //faker adress object
                Address address= faker.address();
                String cityName = address.city();
                String postalCode= address.zipCode();

                /*Create City by picking a random country in tee existing one*/
                cityDao.save(new City(cityName, postalCode, this.countries.get(random.nextInt(countries.size()))));
            }
        }
    }

    private void addCompanies(){
        //only if the table Company is empty
        if(companyDao.count()==0){
            /*get all cities*/
            this.cities = cityDao.findAll();

            //Create 30 companies
            for(Integer i=0 ; i<10; i++){

                /* Create faker address object*/
                Address address= faker.address();
                /* create random company data*/
                String name= faker.company().name();
                String siren= faker.number().digits(9);

                /*Create company by picking a random city in the existing one*/
                companyDao.save(new Company(siren,name,address.streetAddress(),address.streetAddressNumber(), address.secondaryAddress(),cities.get(random.nextInt(cities.size()))));

            }
        }
    }

    private void addUsers(){


         //Only if the table is empty
        if(userDao.count()==0){

            /*Retrieve companies ind b*/
            this.companies=companyDao.findAll();

            //create 30 users//
            for(Integer i=0; i<10; i++) {

                /*Create faker name object*/
                Name name = faker.name();
                /*Create faker internet object*/
                Internet internet = faker.internet();


                //create creation date so we can get user in the past
                Instant createdAt= Instant.now().minus(Duration.ofDays(random.nextInt(1200)));

                //retrieve phone
                String phoneNumber = faker.number().digits(9);
                //retrieve international code and get ride of the +
                String interCode= faker.number().digits(3);

                /*Create User by picking random company in existing one*/
                User user = new User(name.firstName(),name.lastName(),internet.emailAddress(),internet.password(), phoneNumber, interCode,companies.get(random.nextInt(companies.size())));
                /*Set genereatd created date*/
                user.setUserCreatedAt(createdAt);
                //save user in db
                userDao.save(user);

            }
        }
    }

    private void addRoles(){
        //Only if the table is empty
        if(roleDao.count()==0){

            /*create an array with each roles roles*/

            String[] roles = {"Client","Mod","Admin"};

            //create a new role for each index of the array
            for(String role:roles){
                roleDao.save(new Role(role));
            }

            //retrieve all users in db
            this.users = userDao.findAll();
            List<Role> rolesList= roleDao.findAll();

            //distribute roles to each Users
           for(User user:users){
                System.out.println();

                //generate a number between 1 and 3 ( for index of roles array)
                Integer nbRoles= random.nextInt(3);
                for(Integer i=0; i<nbRoles; i++){
                    user.setRole(rolesList.get(i));
                    userDao.saveAndFlush(user);
                }
            }
        }
    }


    private void addZones(){
        //only if the table is empty()
        if(zoneDao.count()==0){

           /*Create 30 zone */
            for(Integer i=0; i<10; i++){

                String name=faker.lordOfTheRings().location();
                String desc= faker.hitchhikersGuideToTheGalaxy().marvinQuote();
                Internet internet =faker.internet();
                Double price = faker.number().randomDouble(2,25,185);

                zoneDao.save(new Zone(name,price,desc,internet.url(),internet.slug()));
            }
        }
    }

    private void addStops(){

        //only if table is empty
        if(stopDao.count()==0){
            /*get all zone*/
            List<Zone> zones= zoneDao.findAll();

            //create between 5 and 20 stops per zone
            for(Zone zone:zones){
                //generate random number between 5 and 20
                Integer nbStops = random.ints(5,21).findFirst().getAsInt();
                /*Create a number of stops equals to nbStops in the zone*/
                for(Integer i=0; i<nbStops;i++){

                    stopDao.save(new Stop(faker.funnyName().name(), faker.internet().ipV4Address(),zone));
                }
            }
        }
    }

    private void addTimeSlots() {

        //only if table is empty
        if(timeSDao.count()==0){

            //create eachTime slot available ( between 6 am and 20pm)
            for(Integer i=6; i<20; i++){
                Double price= faker.number().randomDouble(2,24,80);
                //turn i into string to instantiate time slot localTime
                String hour= null;
                if(i<10){
                    hour = "0"+String.valueOf(i);
                }
                else{
                    hour = String.valueOf(i);
                }

                //create the time slot
                timeSDao.save(new TimeSlot(LocalTime.parse(hour+":00:00"),price));
            }
        }
    }

    private void addAds(){
        //only if table is empty
        if(adDao.count()==0){

            /*Create 50 ads*/
            for(Integer i=0; i<10; i++){

                //*Generate random number for day to add to start the campaign to todays date*/
                Instant startAt = Instant.now().plus(Duration.ofDays(random.nextInt(90)));
                Instant createdAt = Instant.now().minus(Duration.ofDays(random.nextInt(1200)));

                String name= faker.commerce().productName();
                String url= faker.internet().url();
                String alt= faker.internet().slug();


                Instant paidAt= null;

                //add random creation of  value paidAt, so we can have unpaid Ads available and paid ads
                if(random.nextInt(3)==2){
                    paidAt= createdAt.plus(Duration.ofDays(random.nextInt(30)));
                }

                //instantiate object to create
                Advertisment ad = new Advertisment(name, startAt,(random.nextInt(60))+1,url,alt,paidAt,users.get(random.nextInt(users.size())));
                //change date of creation to get either now or in the past til 1200 days earlier
                ad.setAdCreatedAt(createdAt);

                //save ad in db
                adDao.save(ad);
            }
        }
    }

    private void addTimeSlotsLists(){
        //only if table is empty
        if(tslDao.count()==0){

            /*retrieve all ads*/
            this.ads= adDao.findAll();
            /*retirves all time slot*/
            List<TimeSlot> ts= timeSDao.findAll();

            /*retrives all zone*/
            List<Zone>  zones = zoneDao.findAll();

            //generate Time slots list for each ads
            for(Advertisment ad:ads){
                //generate random number of time slots
                Integer nbTS= random.nextInt(14);

                //generetate a random number of Time slot for each ads equals to nbTs
                for(Integer i=0;i<nbTS;i++){

                    //create time slot list by picking a random zone for each time slot
                    tslDao.save(new TimeSlotList(ts.get(i),ad,zones.get(random.nextInt(zones.size()))));

                }
            }
        }
    }

    private void addPaymentMethods() {

        //only if table is empty
        if (payMDao.count() == 0) {

            /*create buisness faker object*/
            Business card = faker.business();
            Name name = faker.name();
            /*Generate a limited number of company with payment methods so we can have company without any*/
            Integer nbCompany = (random.nextInt(this.companies.size()))-2;
            if (nbCompany < 0) {
                nbCompany = 0;
            }

            //create limited number of payment method nut make saure there's at least one
            for (Integer i = 0; i <= nbCompany; i++) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                LocalDate expDate = LocalDate.parse(card.creditCardExpiry(),formatter);

                payMDao.save(new PaymentMethod(card.creditCardNumber(),expDate, faker.number().digits(3), name.firstName(), name.lastName(), this.companies.get(random.nextInt(companies.size()))));
            }
        }
    }

    private void addInvoices(){
        //only if table is empty
        if(invoiceDao.count()==0){
            //for each ads create an invoice
            for(Advertisment ad:this.ads){

                String desc = faker.company().bs();
                Instant createdAt = ad.getAdCreatedAt();
                Instant signedAt= createdAt.plus(Duration.ofDays(30));

                /*if signed at is in the futur signed at is null*/
                if(signedAt.isAfter(Instant.now())){
                    signedAt=null;
                }
                String signature= faker.name().fullName();

                /*create new invoice*/
                Invoice invoice= new Invoice(desc,signedAt,signature,ad);
                /*change the creation date*/
                invoice.setInvoiceCreatedAt(createdAt);
                /*save in db*/
                invoiceDao.save(invoice);

            }
        }
    }

}