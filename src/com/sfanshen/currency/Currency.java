package com.sfanshen.currency;

import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.upgrade.BoostUpgrade;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class Currency{
  public String name;
  public BigNum amount;
  //Additive boosts are applied before multiplicative ones in the form of baseBoost and finalBoost
  BigNum finalBoost;
  BigNum additiveBoost;
  BigNum multiplicativeBoost;
  BigNum exponentialBoost;
  
  public ArrayList<BoostUpgrade> additiveUpgrades;
  public ArrayList<BoostUpgrade> multiplicativeUpgrades;
  ArrayList<BoostUpgrade> exponentialUpgrades;
  
  public Picture icon;


  Color backgroundColor = new Color(13,31,45);
  Color sunYellow = new Color(230,241,74);
  Color bageOrSmthnn = new Color(239,188,155);
  Color pinkRed = new Color(229, 83, 129);
  Color plainBlue = new Color(37, 110, 255);
  Color b2bGrey = new Color(178,176,155);
  Color darkishBrown = new Color(107,39,55);
  Color darkTurquoise = new Color(21,112,122);
  Color googleHighlight = new Color(175, 203, 255);
  Color tintedGold = new Color(155,137,49);

  public Currency(String name, Picture icon){
    this.name = name;
    this.amount = new BigNum(0);
    additiveUpgrades = new ArrayList<>();
    multiplicativeUpgrades = new ArrayList<>();
    exponentialUpgrades = new ArrayList<>();
    
    this.finalBoost = new BigNum(1);
    this.additiveBoost = new BigNum(1);
    this.multiplicativeBoost = new BigNum(1);
    this.exponentialBoost = new BigNum(1);
    
    this.icon = icon;
  }
  
  public void set(BigNum amount){
    this.amount.set(amount);
  }
  
  public void increase(BigNum n){
    n.multiply(this.finalBoost);
    this.amount.add(n);
  }
  public void increase(int n){
    this.amount.add(BigNum.multiply(this.finalBoost, new BigNum(n)));
  }
  public void increase(){
    this.amount.add(this.finalBoost);
  }
  
  
  public void calculateBoost(){
    this.finalBoost.set(1);
    
    this.finalBoost.add(this.additiveBoost);
    this.finalBoost.multiply(this.multiplicativeBoost);
    this.finalBoost.pow(this.exponentialBoost);
  }
  
  public void calculateAdditiveBoost(){
     for (BoostUpgrade additiveUpgrade : this.additiveUpgrades){
       if (additiveUpgrade.level > 0){
         this.additiveBoost.add(additiveUpgrade.calculateBoost(this));
       }
    }
  }
  
  public void calculateMultiplicativeBoost(){
    for (BoostUpgrade multiplicativeUpgrade: multiplicativeUpgrades){
      if (multiplicativeUpgrade.level > 0){
        multiplicativeBoost.multiply(multiplicativeUpgrade.calculateBoost(this));
      }
    }
  }
  
  public void calculateExponentialBoost(){
    for (BoostUpgrade exponentialUpgrade: exponentialUpgrades){
      if (exponentialUpgrade.level > 0){
        exponentialBoost.multiply(exponentialUpgrade.calculateBoost(this));
      
      }
    }
  }
}
