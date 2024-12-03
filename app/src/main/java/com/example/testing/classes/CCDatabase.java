package com.example.testing.classes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testing.interfaces.database_interfaces.Account_Dao;
import com.example.testing.interfaces.database_interfaces.Admin_Dao;
import com.example.testing.interfaces.database_interfaces.Camp_Dao;
import com.example.testing.interfaces.database_interfaces.DonationAllocation_Dao;
import com.example.testing.interfaces.database_interfaces.Donation_Dao;
import com.example.testing.interfaces.database_interfaces.MedicalRequest_Dao;
import com.example.testing.interfaces.database_interfaces.NGO_Dao;
import com.example.testing.interfaces.database_interfaces.Project_Dao;
import com.example.testing.interfaces.database_interfaces.Ratings_Dao;
import com.example.testing.interfaces.database_interfaces.Report_Dao;
import com.example.testing.interfaces.database_interfaces.User_Dao;
import com.example.testing.interfaces.database_interfaces.Volunteers_Dao;

import java.util.Calendar;


@androidx.room.Database(entities = {Account.class,User.class,NGO.class,Project.class,Donation.class,DonationAllocation.class,Camp.class,Admin.class, AppliedVolunteer.class,Report.class,Ratings.class, MedicalRequest.class},version = 1)
public abstract class CCDatabase extends RoomDatabase {
    public abstract Account_Dao accountDao();
    public abstract User_Dao userDao();
    public abstract NGO_Dao NGODao();
    public abstract Project_Dao projectDao();
    public abstract Donation_Dao donationDao();
    public abstract DonationAllocation_Dao donationAllocationDao();
    public abstract Camp_Dao campDao();
    public abstract Admin_Dao adminDao();
    public abstract Volunteers_Dao volunteersDao();
    public abstract Report_Dao reportDao();
    public abstract Ratings_Dao ratingsDao();
    public abstract MedicalRequest_Dao medicalRequestDao();

    private static CCDatabase instance;

    public static synchronized CCDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,CCDatabase.class,"Crisis_Control_Db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallBack)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateInitialData(instance).execute();
        }
    };

    public void deleteAllData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                accountDao().deleteAll();
                userDao().deleteAll();
                NGODao().deleteAll();
                projectDao().deleteAll();
                donationDao().deleteAll();
                donationAllocationDao().deleteAll();
                campDao().deleteAll();
                adminDao().deleteAll();
                volunteersDao().deleteAll();
                reportDao().deleteAll();
                ratingsDao().deleteAll();
                medicalRequestDao().deleteAll();
            }
        }).start();
    }

    private static class populateInitialData extends AsyncTask<Void,Void,Void>{
        private Account_Dao accountDao;
        private User_Dao userDao;
        private NGO_Dao NGODao;
        private Project_Dao projectDao;
        private Donation_Dao donationDao;
        private Camp_Dao campDao;
        private Admin_Dao adminDao;
        private Volunteers_Dao volunteersDao;
        private Report_Dao reportDao;
        private Ratings_Dao ratingsDao;
        private MedicalRequest_Dao medicalRequestDao;

        public populateInitialData(CCDatabase db){
            this.accountDao=db.accountDao();
            this.userDao=db.userDao();
            this.NGODao=db.NGODao();
            this.projectDao=db.projectDao();
            this.donationDao=db.donationDao();
            this.campDao=db.campDao();
            this.adminDao= db.adminDao();
            this.volunteersDao = db.volunteersDao();
            this.reportDao = db.reportDao();
            this.ratingsDao = db.ratingsDao();
            this.medicalRequestDao=db.medicalRequestDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2002);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 25);
            User u1=new User(1,"zainakbarsukhera@gmail.com","zain2121","Zain Akbar",cal.getTime(),"15-E, Phase 1, DHA, Lahore",User.USER_TYPE_DONOR);
            userDao.insertUserAccount(u1,u1);
            cal.set(Calendar.YEAR, 1990);
            cal.set(Calendar.MONTH, Calendar.MAY);
            cal.set(Calendar.DAY_OF_MONTH, 16);
            User u2 = new User(2,"affectee1@gmail.com","affectee1","Ahmed Akram",cal.getTime(),"Saidu Sharif, Swat",User.USER_TYPE_AFFECTEE);
            userDao.insertUserAccount(u2,u2);
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.AUGUST);
            cal.set(Calendar.DAY_OF_MONTH, 18);
            User u3 = new User(3,"volunteer1@gmail.com","volunteer1","Ammar Khan",cal.getTime(),"Main Saddar, Quetta",User.USER_TYPE_VOLUNTEER);
            userDao.insertUserAccount(u3,u3);
            User u4 = new User(4,"mustaqimafzal@gmail.com","musti123","Mustaqim Afzal",cal.getTime(),"Johar Town Lahore",User.USER_TYPE_DONOR);
            userDao.insertUserAccount(u4,u4);
            NGO n1 = new NGO(5,"alKhidmatFoundation","akf12345","Alkhidmat Foundation","Alkhidmat Foundation is one of the leading, non-profit organization, fully dedicated to humanitarian services since 1990.", "Alkhidmat Foundation Headoffice, 3km Khayaban-e-Jinnah, Lahore, Punjab, Pakistan",	true,	true,	4.3,2,"02140100861151","Meezan Bank");
            NGODao.insertNGOAccount(n1,n1);
            NGO n2 = new NGO(6,"saylaniWelfare","sw12345","SaylaniWelfare"	,"Saylani Welfare is on the ground and already working with local communities to assess how best to support underprivileged families in more than 63 areas of day to day lives.",	"A-25, Bahadurabad Chowrangi Karachi, Pakistan",	true,	true,	4.7	,5,"0011036001","Dubai Islamic Bank");
            NGODao.insertNGOAccount(n2,n2);
            NGO n3 = new NGO(7,"theCitizensFoundation","tcf12345","The Citizens Foundation",	"The journey of a thousand schools began with a single idea and soon turned into a global movement.",	"Plot No. 20, Sector - 14, Near Brookes Chowrangi, Korangi Industrial Area,\n" +
                    "Karachi 74900, Pakistan.",	true,	true,	4.1,	7,"0010006028260011","Allied Bank");
            NGODao.insertNGOAccount(n3,n3);
            cal.set(Calendar.YEAR, 2024);
            cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 11);
            Project p1 = new Project("Education for Balochistan",	"Asking for donations to build schools in rural centers around Quetta that will help lower the illiteracy rate in the province.", 	7,	450000,	0,	cal.getTime());
            projectDao.insertProject(p1);
            cal.set(Calendar.YEAR,2024 );
            cal.set(Calendar.MONTH, Calendar.MAY);
            cal.set(Calendar.DAY_OF_MONTH, 18);
            Project p2 = new Project("Earthquake Relief Fund",	"Asking for food, clothes and financial donations for the earthquake victims of KPK. Volunteers are also needed to fulfill this campaign",	5,	200000,	15,	cal.getTime());
            projectDao.insertProject(p2);
            cal.set(Calendar.YEAR, 2024);
            cal.set(Calendar.MONTH, Calendar.NOVEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 23);
            Project p3= new Project("Feed the Poor",	"Asking for donations to feed the poor people on the streets of Lahore", 	6,	50000,	10	,cal.getTime());
            projectDao.insertProject(p3);
            cal.set(Calendar.YEAR, 2024);
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 2);
            Donation d = new Donation( 	1,	2,	cal.getTime(),3500,1);
            donationDao.insertDonation(d);
            Donation d1= new Donation(1,	1,	cal.getTime(),	1000,0);
            donationDao.insertDonation(d1);
            Donation d2 = new Donation(4,	2,	cal.getTime(),	50000,0);
            donationDao.insertDonation(d2);

            Coordinates co1 = new Coordinates(31.4845, 74.3263,"Model Town Park");
            Coordinates co2 = new Coordinates(31.4776, 74.3123,"Motia Park Model Town");

            Camp c1 = new Camp(5,co1);
            campDao.insertCamp(c1);

            Camp c2 = new Camp(5,co2);
            campDao.insertCamp(c2);

            Coordinates co3 = new Coordinates(31.4739, 74.3066,"Faisal Town Park");
            Camp c3 = new Camp(6,co3);
            campDao.insertCamp(c3);

            Coordinates co4 = new Coordinates(31.4616, 74.2872,"Johar Town Park");
            Camp c4 = new Camp(6,co4);
            campDao.insertCamp(c4);

            Coordinates co5 = new Coordinates(31.4688132, 74.249968,"J-3 Park Johar Town");
            Camp c5 = new Camp(6,co5);
            campDao.insertCamp(c5);

            Coordinates co6 = new Coordinates(31.4720, 74.2622,"H-3 Park Johar Town");
            Camp c6 = new Camp(7,co6);
            campDao.insertCamp(c6);

            Admin ad1 = new Admin(accountDao.getNewAccountID(),"123456789","admin1","0123456789012","Zain Akbar");
            adminDao.insertAdmin(ad1,ad1);

            Report r = new Report(n1.accID, u2.accID,"They do not respond to email and their allocations are not updated.",false);
            Report r1 = new Report(n3.accID, u3.accID,"Their allocations are not updated.",true);
            reportDao.insertReport(r);
            reportDao.insertReport(r1);

            return null;
        }
    }


}
