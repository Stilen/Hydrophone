package com.example.pedro.marsensing;

public class Config {
    private int id;
    private String name;
    private int gain;
    private int interval;
    private int mode;
    private int duration;
    private int nfiles;
    private int adc;
    private int bits;

    public Config()
    {
    }
    public Config(int id,String name,int gain,int interval,int mode,int duration,int nfiles,int adc,int bits)
    {
        this.id=id;
        this.name=name;
        this.gain=gain;
        this.interval=interval;
        this.mode=mode;
        this.duration=duration;
        this.nfiles=nfiles;
        this.adc=adc;
        this.bits=bits;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGain(int gain){
        this.gain = gain;
    }
    public void setInterval(int interval){
        this.interval = interval;
    }
    public void setMode(int mode){
        this.mode = mode;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setNfiles(int nfiles){
        this.nfiles = nfiles;
    }
    public void setAdc(int adc){
        this.adc = adc;
    }
    public void setBits(int bits){
        this.bits = bits;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getGain(){
        return gain;
    }
    public int getInterval(){
        return interval;
    }
    public int getMode(){
        return mode;
    }
    public int getDuration(){
        return duration;
    }
    public int getNfiles(){
        return nfiles;
    }
    public int getAdc(){
        return adc;
    }
    public int getBits(){
        return bits;
    }
}