package ksznldr.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RECORD")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
    @XmlAttribute(name = "RECORDID")
    private String recordId;
    @XmlElement(name = "PUBLICID")
    private String publicId;
    @XmlElement(name = "LNAME")
    private String lName;
    @XmlElement(name = "FNAME")
    private String fName;
    @XmlElement(name = "MNAME")
    private String mName;
    @XmlElement(name = "SNILS")
    private String snils;
    @XmlElement(name = "BDATE")
    private String bdate;
    @XmlElement(name = "SEX")
    private String sex;
    @XmlElement(name = "KLADR")
    private String kladr;
    @XmlElement(name = "DISTRICT")
    private String district;
    @XmlElement(name = "CITY")
    private String city;
    @XmlElement(name = "PUNKT")
    private String punkt;
    @XmlElement(name = "STREET")
    private String street;
    @XmlElement(name = "BNUM")
    private String bnum;
    @XmlElement(name = "WING")
    private String wing;
    @XmlElement(name = "APPNUM")
    private String appnum;
    @XmlElement(name = "WGCODE")
    private String wgcode;
    @XmlElement(name = "STATUS")
    private String status;
    @XmlElement(name = "REASON")
    private String reason;
    @XmlElement(name = "SCOMMENT")
    private String scomment;
    @XmlElement(name = "L_SCHETS")
    private String lschets;

    public Record() {
    }

    public Record(String recordId, String publicId, String lName, String fName, String mName, String snils, String bdate, String sex, String kladr, String district, String city, String punkt, String street, String bnum, String wing, String appnum, String wgcode, String status, String reason, String scomment, String lschets) {
        this.recordId = recordId;
        this.publicId = publicId;
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;
        this.snils = snils;
        this.bdate = bdate;
        this.sex = sex;
        this.kladr = kladr;
        this.district = district;
        this.city = city;
        this.punkt = punkt;
        this.street = street;
        this.bnum = bnum;
        this.wing = wing;
        this.appnum = appnum;
        this.wgcode = wgcode;
        this.status = status;
        this.reason = reason;
        this.scomment = scomment;
        this.lschets = lschets;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId='" + recordId + '\'' +
                ", publicId='" + publicId + '\'' +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", snils='" + snils + '\'' +
                ", bdate='" + bdate + '\'' +
                ", sex='" + sex + '\'' +
                ", kladr='" + kladr + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", punkt='" + punkt + '\'' +
                ", street='" + street + '\'' +
                ", bnum='" + bnum + '\'' +
                ", wing='" + wing + '\'' +
                ", appnum='" + appnum + '\'' +
                ", wgcode='" + wgcode + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", scomment='" + scomment + '\'' +
                ", lschets='" + lschets + '\'' +
                '}';
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getSnils() {
        return snils;
    }

    public String getBdate() {
        return bdate;
    }

    public String getSex() {
        return sex;
    }

    public String getKladr() {
        return kladr;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getPunkt() {
        return punkt;
    }

    public String getStreet() {
        return street;
    }

    public String getBnum() {
        return bnum;
    }

    public String getWing() {
        return wing;
    }

    public String getAppnum() {
        return appnum;
    }

    public String getWgcode() {
        return wgcode;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getScomment() {
        return scomment;
    }

    public String getLschets() {
        return lschets;
    }
}