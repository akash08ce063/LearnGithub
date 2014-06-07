/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GameCharacter {
    private String imagePath;
    private String name;
    private int health;
    private int armor;
    private int attackPts;
    private int attackRange;
    private int movement;
    private int id;
    private int position ;
    
    
    
    public void setID(int id){
        this.id = id ;
    }
    public int getID(){
        return id ;
    }
    
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position ;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttackPts() {
        return attackPts;
    }

    public void setAttackPts(int attackPts) {
        this.attackPts = attackPts;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
}
