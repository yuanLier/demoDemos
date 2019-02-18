package com.demo.demogenerator.model;

import java.util.Date;

public class GameInfo {
    private Long id;

    private String gameName;

    private Long gameCreatorId;

    private Integer gameMaxEnterprise;

    private Integer gameEnterpriseNumber;

    private Integer gameEnterpriseAliveNumber;

    private Integer gameYears;

    private Integer gamePeriodsOfYear;

    private Integer gameCurrentPeriod;

    private Integer gameStatus;

    private Date gameTime;

    public GameInfo(Long id, String gameName, Long gameCreatorId, Integer gameMaxEnterprise, Integer gameEnterpriseNumber, Integer gameEnterpriseAliveNumber, Integer gameYears, Integer gamePeriodsOfYear, Integer gameCurrentPeriod, Integer gameStatus, Date gameTime) {
        this.id = id;
        this.gameName = gameName;
        this.gameCreatorId = gameCreatorId;
        this.gameMaxEnterprise = gameMaxEnterprise;
        this.gameEnterpriseNumber = gameEnterpriseNumber;
        this.gameEnterpriseAliveNumber = gameEnterpriseAliveNumber;
        this.gameYears = gameYears;
        this.gamePeriodsOfYear = gamePeriodsOfYear;
        this.gameCurrentPeriod = gameCurrentPeriod;
        this.gameStatus = gameStatus;
        this.gameTime = gameTime;
    }

    public GameInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public Long getGameCreatorId() {
        return gameCreatorId;
    }

    public void setGameCreatorId(Long gameCreatorId) {
        this.gameCreatorId = gameCreatorId;
    }

    public Integer getGameMaxEnterprise() {
        return gameMaxEnterprise;
    }

    public void setGameMaxEnterprise(Integer gameMaxEnterprise) {
        this.gameMaxEnterprise = gameMaxEnterprise;
    }

    public Integer getGameEnterpriseNumber() {
        return gameEnterpriseNumber;
    }

    public void setGameEnterpriseNumber(Integer gameEnterpriseNumber) {
        this.gameEnterpriseNumber = gameEnterpriseNumber;
    }

    public Integer getGameEnterpriseAliveNumber() {
        return gameEnterpriseAliveNumber;
    }

    public void setGameEnterpriseAliveNumber(Integer gameEnterpriseAliveNumber) {
        this.gameEnterpriseAliveNumber = gameEnterpriseAliveNumber;
    }

    public Integer getGameYears() {
        return gameYears;
    }

    public void setGameYears(Integer gameYears) {
        this.gameYears = gameYears;
    }

    public Integer getGamePeriodsOfYear() {
        return gamePeriodsOfYear;
    }

    public void setGamePeriodsOfYear(Integer gamePeriodsOfYear) {
        this.gamePeriodsOfYear = gamePeriodsOfYear;
    }

    public Integer getGameCurrentPeriod() {
        return gameCurrentPeriod;
    }

    public void setGameCurrentPeriod(Integer gameCurrentPeriod) {
        this.gameCurrentPeriod = gameCurrentPeriod;
    }

    public Integer getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Integer gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }
}