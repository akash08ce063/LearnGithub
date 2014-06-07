/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Ring implements Item{
    private String name;
    private int incHealth;
    private int incArmour;
    private int incAttack;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncHealth() {
        return incHealth;
    }

    public void setIncHealth(int incHealth) {
        this.incHealth = incHealth;
    }

    public int getIncArmour() {
        return incArmour;
    }

    public void setIncArmour(int incArmour) {
        this.incArmour = incArmour;
    }

    public int getIncAttack() {
        return incAttack;
    }

    public void setIncAttack(int incAttack) {
        this.incAttack = incAttack;
    }
}
