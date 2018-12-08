package com.isoftstone.pmit.project.hrbp.entity;

public class TrainingPassScore {
    /**培训名称*/
    private String trainingName;
    /**通过的分数*/
    private int passScores;
    /**学分*/
    private int trainingCredits;
    /**实际分数*/
    private int actualScore;

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public int getPassScores() {
        return passScores;
    }

    public void setPassScores(int passScores) {
        this.passScores = passScores;
    }

    public int getTrainingCredits() {
        return trainingCredits;
    }

    public void setTrainingCredits(int trainingCredits) {
        this.trainingCredits = trainingCredits;
    }

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }
}
