package com.bodejidi;

public class Contact
{
    private Long id;
    private String name;
    private String mobile;
    private String vpmn;
    private String email;
    private String homeAddress;
    private String officeAddress;
    private String memo;
    private String job;
    private String jobLevel;

    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }
     
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getMobile()
    {
        return mobile;
    }
     
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }
          
    public void setHomeAddress(String homeAddress)
    {
        this.homeAddress = homeAddress;
    }
    public String getHomeAddress()
    {
        return homeAddress;
    }
    public void setOfficeAddress(String officeAddress)
    {
        this.officeAddress = officeAddress;
    }
    public String getOfficeAddress()
    {
        return officeAddress;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setJob(String job)
    {
        this.job = job;
    }
    public String getJob()
    {
        return job;
    }
    public void setJobLevel(String jobLevel)
    {
        this.jobLevel = jobLevel;
    }
    public String getJobLevel()
    {
        return jobLevel;
    }
}

