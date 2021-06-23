package com.example.clockapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

class City implements Serializable {

    private String id;
    private String cityName;
    private double timeZone;
    private Boolean status;
    private String counrtyCode;
    private final transient ICityDAO dao;

    public City(String name, double zone,String country, ICityDAO dao){
        this.id = UUID.randomUUID().toString();
        this.cityName=name;
        this.timeZone=zone;
        this.status=false;
        this.counrtyCode=country;
        this.dao=dao;
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return this.cityName;
    }

    public double getZone(){
        return timeZone;
    }

    public Boolean getCheck(){
        return this.status;
    }

    public void setCheck(Boolean val){
        this.status=val;
    }

    public String getCounrtyCode(){
        return counrtyCode;
    }

    public void updateCheck(){
        dao.updateCheck(this);
    }

    public int getFlag(){
        switch (this.counrtyCode) {
            case "ad": return R.drawable.ad;
            case "ae": return R.drawable.ae;
            case "af": return R.drawable.af;
            case "ag": return R.drawable.ag;
            case "ai": return R.drawable.ai;
            case "al": return R.drawable.al;
            case "am": return R.drawable.am;
            case "ao": return R.drawable.ao;
            case "aq": return R.drawable.aq;
            case "ar": return R.drawable.ar;
            case "as": return R.drawable.as;
            case "at": return R.drawable.at;
            case "au": return R.drawable.au;
            case "aw": return R.drawable.aw;
            case "ax": return R.drawable.ax;
            case "az": return R.drawable.az;
            case "ba": return R.drawable.ba;
            case "bb": return R.drawable.bb;
            case "bd": return R.drawable.bd;
            case "be": return R.drawable.be;
            case "bf": return R.drawable.bf;
            case "bg": return R.drawable.bg;
            case "bh": return R.drawable.bh;
            case "bi": return R.drawable.bi;
            case "bj": return R.drawable.bj;
            case "bl": return R.drawable.bl;
            case "bm": return R.drawable.bm;
            case "bn": return R.drawable.bn;
            case "bo": return R.drawable.bo;
            case "bq": return R.drawable.bq;
            case "br": return R.drawable.br;
            case "bs": return R.drawable.bs;
            case "bt": return R.drawable.bt;
            case "bv": return R.drawable.bv;
            case "bw": return R.drawable.bw;
            case "by": return R.drawable.by;
            case "bz": return R.drawable.bz;
            case "ca": return R.drawable.ca;
            case "cc": return R.drawable.cc;
            case "cd": return R.drawable.cd;
            case "cf": return R.drawable.cf;
            case "cg": return R.drawable.cg;
            case "ch": return R.drawable.ch;
            case "ci": return R.drawable.ci;
            case "ck": return R.drawable.ck;
            case "cl": return R.drawable.cl;
            case "cm": return R.drawable.cm;
            case "cn": return R.drawable.cn;
            case "co": return R.drawable.co;
            case "cr": return R.drawable.cr;
            case "cu": return R.drawable.cu;
            case "cv": return R.drawable.cv;
            case "cw": return R.drawable.cw;
            case "cx": return R.drawable.cx;
            case "cy": return R.drawable.cy;
            case "cz": return R.drawable.cz;
            case "de": return R.drawable.de;
            case "dj": return R.drawable.dj;
            case "dk": return R.drawable.dk;
            case "dm": return R.drawable.dm;
            case "do1": return R.drawable.do1;
            case "dz": return R.drawable.dz;
            case "ec": return R.drawable.ec;
            case "ee": return R.drawable.ee;
            case "eg": return R.drawable.eg;
            case "eh": return R.drawable.eh;
            case "er": return R.drawable.er;
            case "es": return R.drawable.es;
            case "et": return R.drawable.et;
            case "fi": return R.drawable.fi;
            case "fj": return R.drawable.fj;
            case "fk": return R.drawable.fk;
            case "fm": return R.drawable.fm;
            case "fo": return R.drawable.fo;
            case "fr": return R.drawable.fr;
            case "ga": return R.drawable.ga;
            case "gb": return R.drawable.gb;
            case "gd": return R.drawable.gd;
            case "ge": return R.drawable.ge;
            case "gf": return R.drawable.gf;
            case "gg": return R.drawable.gg;
            case "gh": return R.drawable.gh;
            case "gi": return R.drawable.gi;
            case "gl": return R.drawable.gl;
            case "gm": return R.drawable.gm;
            case "gn": return R.drawable.gn;
            case "gp": return R.drawable.gp;
            case "gq": return R.drawable.gq;
            case "gr": return R.drawable.gr;
            case "gs": return R.drawable.gs;
            case "gt": return R.drawable.gt;
            case "gu": return R.drawable.gu;
            case "gw": return R.drawable.gw;
            case "gy": return R.drawable.gy;
            case "hk": return R.drawable.hk;
            case "hm": return R.drawable.hm;
            case "hn": return R.drawable.hn;
            case "hr": return R.drawable.hr;
            case "ht": return R.drawable.ht;
            case "hu": return R.drawable.hu;
            case "id": return R.drawable.id;
            case "ie": return R.drawable.ie;
            case "il": return R.drawable.il;
            case "im": return R.drawable.im;
            case "in": return R.drawable.in;
            case "io": return R.drawable.io;
            case "iq": return R.drawable.iq;
            case "ir": return R.drawable.ir;
            case "is": return R.drawable.is;
            case "it": return R.drawable.it;
            case "je": return R.drawable.je;
            case "jm": return R.drawable.jm;
            case "jo": return R.drawable.jo;
            case "jp": return R.drawable.jp;
            case "ke": return R.drawable.ke;
            case "kg": return R.drawable.kg;
            case "kh": return R.drawable.kh;
            case "ki": return R.drawable.ki;
            case "km": return R.drawable.km;
            case "kn": return R.drawable.kn;
            case "kp": return R.drawable.kp;
            case "kr": return R.drawable.kr;
            case "kw": return R.drawable.kw;
            case "ky": return R.drawable.ky;
            case "kz": return R.drawable.kz;
            case "la": return R.drawable.la;
            case "lb": return R.drawable.lb;
            case "lc": return R.drawable.lc;
            case "li": return R.drawable.li;
            case "lk": return R.drawable.lk;
            case "lr": return R.drawable.lr;
            case "ls": return R.drawable.ls;
            case "lt": return R.drawable.lt;
            case "lu": return R.drawable.lu;
            case "lv": return R.drawable.lv;
            case "ly": return R.drawable.ly;
            case "ma": return R.drawable.ma;
            case "mc": return R.drawable.mc;
            case "md": return R.drawable.md;
            case "me": return R.drawable.me;
            case "mf": return R.drawable.mf;
            case "mg": return R.drawable.mg;
            case "mh": return R.drawable.mh;
            case "mk": return R.drawable.mk;
            case "ml": return R.drawable.ml;
            case "mm": return R.drawable.mm;
            case "mn": return R.drawable.mn;
            case "mo": return R.drawable.mo;
            case "mp": return R.drawable.mp;
            case "mq": return R.drawable.mq;
            case "mr": return R.drawable.mr;
            case "ms": return R.drawable.ms;
            case "mt": return R.drawable.mt;
            case "mu": return R.drawable.mu;
            case "mv": return R.drawable.mv;
            case "mw": return R.drawable.mw;
            case "mx": return R.drawable.mx;
            case "my": return R.drawable.my;
            case "mz": return R.drawable.mz;
            case "na": return R.drawable.na;
            case "nc": return R.drawable.nc;
            case "ne": return R.drawable.ne;
            case "nf": return R.drawable.nf;
            case "ng": return R.drawable.ng;
            case "ni": return R.drawable.ni;
            case "nl": return R.drawable.nl;
            case "no": return R.drawable.no;
            case "np": return R.drawable.np;
            case "nr": return R.drawable.nr;
            case "nu": return R.drawable.nu;
            case "nz": return R.drawable.nz;
            case "om": return R.drawable.om;
            case "pa": return R.drawable.pa;
            case "pe": return R.drawable.pe;
            case "pf": return R.drawable.pf;
            case "pg": return R.drawable.pg;
            case "ph": return R.drawable.ph;
            case "pk": return R.drawable.pk;
            case "pl": return R.drawable.pl;
            case "pm": return R.drawable.pm;
            case "pn": return R.drawable.pn;
            case "pr": return R.drawable.pr;
            case "ps": return R.drawable.ps;
            case "pt": return R.drawable.pt;
            case "pw": return R.drawable.pw;
            case "py": return R.drawable.py;
            case "qa": return R.drawable.qa;
            case "re": return R.drawable.re;
            case "ro": return R.drawable.ro;
            case "rs": return R.drawable.rs;
            case "ru": return R.drawable.ru;
            case "rw": return R.drawable.rw;
            case "sa": return R.drawable.sa;
            case "sb": return R.drawable.sb;
            case "sc": return R.drawable.sc;
            case "sd": return R.drawable.sd;
            case "se": return R.drawable.se;
            case "sg": return R.drawable.sg;
            case "sh": return R.drawable.sh;
            case "si": return R.drawable.si;
            case "sj": return R.drawable.sj;
            case "sk": return R.drawable.sk;
            case "sl": return R.drawable.sl;
            case "sm": return R.drawable.sm;
            case "sn": return R.drawable.sn;
            case "so": return R.drawable.so;
            case "sr": return R.drawable.sr;
            case "ss": return R.drawable.ss;
            case "st": return R.drawable.st;
            case "sv": return R.drawable.sv;
            case "sx": return R.drawable.sx;
            case "sy": return R.drawable.sy;
            case "sz": return R.drawable.sz;
            case "tc": return R.drawable.tc;
            case "td": return R.drawable.td;
            case "tf": return R.drawable.tf;
            case "tg": return R.drawable.tg;
            case "th": return R.drawable.th;
            case "tj": return R.drawable.tj;
            case "tk": return R.drawable.tk;
            case "tl": return R.drawable.tl;
            case "tm": return R.drawable.tm;
            case "tn": return R.drawable.tn;
            case "to": return R.drawable.to;
            case "tr": return R.drawable.tr;
            case "tt": return R.drawable.tt;
            case "tv": return R.drawable.tv;
            case "tw": return R.drawable.tw;
            case "tz": return R.drawable.tz;
            case "ua": return R.drawable.ua;
            case "ug": return R.drawable.ug;
            case "um": return R.drawable.um;
            case "us": return R.drawable.us;
            case "uy": return R.drawable.uy;
            case "uz": return R.drawable.uz;
            case "va": return R.drawable.va;
            case "vc": return R.drawable.vc;
            case "ve": return R.drawable.ve;
            case "vg": return R.drawable.vg;
            case "vi": return R.drawable.vi;
            case "vn": return R.drawable.vn;
            case "vu": return R.drawable.vu;
            case "wf": return R.drawable.wf;
            case "ws": return R.drawable.ws;
            case "xk": return R.drawable.xk;
            case "ye": return R.drawable.ye;
            case "yt": return R.drawable.yt;
            case "za": return R.drawable.za;
            case "zm": return R.drawable.zm;
            case "zw": return R.drawable.zw;

            //If No Flag, just return Pakistan
            default:
                return R.drawable.pk;
        }
    }

    public void save() {

        if (dao != null) {

            Hashtable<String, String> data = new Hashtable<>();

            data.put("id", id);
            data.put("cityname", cityName);
            data.put("timezone", Double.toString(timeZone));
            data.put("status", Boolean.toString(status));
            data.put("counrtycode", counrtyCode);
            dao.save(data);
        }
    }

    public void load(Hashtable<String,String> data){
        id = data.get("id");
        timeZone = Double.parseDouble(Objects.requireNonNull(data.get("timezone"))); //Converting String to Double
        cityName = data.get("cityname");
        status = data.get("status").equals("true"); //Converting String to Boolean Conversion
        counrtyCode = data.get("counrtycode");
    }

    public static ArrayList<City> load(ICityDAO dao, Boolean check){
        ArrayList<City> Cities = new ArrayList<City>();
        if(dao != null){

            ArrayList<Hashtable<String,String>> objects = dao.load(check);
            for(Hashtable<String,String> obj : objects){
                City city = new City("",0,"",dao); //Dummy Data
                city.load(obj);
                Cities.add(city);
            }
        }
        return Cities;
    }

    public static ArrayList<City> GenerateCities(CitiesDbDAO dao) {

            ArrayList<City> citiesList = new ArrayList<>();


            StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
            StrictMode.setThreadPolicy(tp);

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {

                    String line=null;

                    try {
                        URL url = new URL("https://api.timezonedb.com/v2.1/list-time-zone?key=3ZN5USVAZ546");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.connect();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        line = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                                reader.lines().collect(Collectors.joining()) :
                                "Please use Android Nougat.";

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    String[] allData = line.split("<zones>|</zones>");

                    String allZones=allData[1];

                    String[] splittedZones=allZones.split("<zone>|</zone>");

                        for (int i = 1; i < splittedZones.length; i+=2) {

                            String CountryCode = null;
                            double UTCCode = 0;
                            String cityname = null;

                            String oneZone=splittedZones[i];

                            String[] s2=oneZone.split("<countryCode>|</countryCode>");

                                CountryCode = s2[1];

                                s2 = oneZone.split("<gmtOffset>|</gmtOffset>");
                                UTCCode = Double.parseDouble(s2[1]);

                                s2 = oneZone.split("<zoneName>|</zoneName>");

                                s2 = s2[1].split("/");
                                cityname = s2[1];

                                citiesList.add(new City(cityname, UTCCode / 3600, CountryCode.toLowerCase(), dao));
                        }
                }
            });

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Adding List in Database
            for (City city : citiesList) {
                city.save();
            }

            //get it from database

            return citiesList;
        }
}
