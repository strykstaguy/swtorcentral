package com.stryksta.swtorcentral.data;

public class AbilitiesItem {

    public String ablName;
    public String ablDesc;
    public String ablIsPassive;
    public String ablIconSpec;
    public int ablGlobalCooldownTime;
    public int ablCooldownTime;
    public int ablCastingTime;
    public int ablForceCost;
    public int ablEnergyCost;
    public int ablMinRange;
    public int ablMaxRange;
    public String ablNode;
    public int ablLevelAquired;
    public String clsResource;

    public AbilitiesItem(String ablName, String ablDesc, String ablIsPassive, String ablIconSpec, int ablGlobalCooldownTime, int ablCooldownTime, int ablCastingTime, int ablForceCost, int ablEnergyCost, int ablMinRange, int ablMaxRange, String ablNode, int ablLevelAquired, String clsResource) {
        this.ablName = ablName;
        this.ablDesc = ablDesc;
        this.ablIsPassive = ablIsPassive;
        this.ablIconSpec = ablIconSpec;
        this.ablGlobalCooldownTime = ablGlobalCooldownTime;
        this.ablCooldownTime = ablCooldownTime;
        this.ablCastingTime = ablCastingTime;
        this.ablForceCost = ablForceCost;
        this.ablEnergyCost = ablEnergyCost;
        this.ablMinRange = ablMinRange;
        this.ablMaxRange = ablMaxRange;
        this.ablNode = ablNode;
        this.ablLevelAquired = ablLevelAquired;
        this.clsResource = clsResource;
    }

    public String getAbilityName() {
        return ablName;
    }

    public void setAbilityName(String ablName){
        this.ablName = ablName;
    }

    public String getAbilityDescription() {
        return ablDesc;
    }

    public void setAbilityDescription(String ablDesc){
        this.ablDesc = ablDesc;
    }

    public String getAbilityPassive() {
        return ablIsPassive;
    }

    public void setAbilityPassive(String ablIsPassive){
        this.ablIsPassive = ablIsPassive;
    }

    public String getAbilityIcon() {
        return ablIconSpec;
    }

    public void setAbilityIcon(String ablIconSpec){
        this.ablIconSpec = ablIconSpec;
    }

    public int getGlobalCooldownTime() {
        return ablGlobalCooldownTime;
    }

    public void setGlobalCooldownTime(int ablGlobalCooldownTime){
        this.ablGlobalCooldownTime = ablGlobalCooldownTime;
    }

    public int getCooldownTime() {
        return ablCooldownTime;
    }

    public void setCooldownTime(int ablCooldownTime){
        this.ablCooldownTime = ablCooldownTime;
    }

    public int getCastingTime() {
        return ablCastingTime;
    }

    public void setCastingTime(int ablCastingTime){
        this.ablCastingTime = ablCastingTime;
    }

    public int getForceCost() {
        return ablForceCost;
    }

    public void setForceCost(int ablForceCost){
        this.ablForceCost = ablForceCost;
    }

    public int getEnergyCost() {
        return ablEnergyCost;
    }

    public void setEnergyCost(int ablEnergyCost){
        this.ablEnergyCost = ablEnergyCost;
    }

    public int getMinRange() {
        return ablMinRange;
    }

    public void setMinRange(int ablMinRange){
        this.ablMinRange = ablMinRange;
    }

    public int getMaxRange() {
        return ablMaxRange;
    }

    public void setMaxRange(int ablMaxRange){
        this.ablMaxRange = ablMaxRange;
    }

    public String getNode() {
        return ablNode;
    }

    public void setNode(String ablNode){
        this.ablNode = ablNode;
    }

    public int getLevelAquired() {
        return ablLevelAquired;
    }

    public void setLevelAquired(int ablLevelAquired){
        this.ablLevelAquired = ablLevelAquired;
    }

    public String getClassResource() {
        return clsResource;
    }

    public void setClassResource(String clsResource){
        this.clsResource = clsResource;
    }
}
