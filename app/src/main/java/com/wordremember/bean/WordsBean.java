package com.wordremember.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/11/23.
 */

public class WordsBean extends BmobObject{
    String name;
    String meaning;
    String sentence;
    String sentenceMean;
    String symbol;
    String sentence2;
    String sentenceMeanning2;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSentence2() {
        return sentence2;
    }

    public void setSentence2(String sentence2) {
        this.sentence2 = sentence2;
    }

    public String getSentenceMeanning2() {
        return sentenceMeanning2;
    }

    public void setSentenceMeanning2(String sentenceMeaning2) {
        this.sentenceMeanning2 = sentenceMeaning2;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentenceMean() {
        return sentenceMean;
    }

    public void setSentenceMean(String sentenceMean) {
        this.sentenceMean = sentenceMean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
