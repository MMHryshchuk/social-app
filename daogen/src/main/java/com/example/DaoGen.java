package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGen {
    private static final int VERSION = 4;
    private Schema schema;

    public static void main(String[] args) {
        new DaoGen().start();
    }

    private void start(){
        try {
            schema = new Schema(VERSION, "com.clearsoft.welivre.domain.entities");
            schema.enableKeepSectionsByDefault();


            generateFeed();
            generatePlan();
            generateFollow();

            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void generateFeed(){
        Entity entity = schema.addEntity("Feed");
        entity.addStringProperty("postId").unique();
        entity.addStringProperty("feedType");
        entity.addStringProperty("postContentTxt");
        entity.addStringProperty("postContentImg");
        entity.addStringProperty("postSos");
        entity.addStringProperty("postTimestamp");
        entity.addStringProperty("postFavorites");
        entity.addStringProperty("postComments");
        entity.addStringProperty("postLikes");
        entity.addStringProperty("postShares");
        entity.addStringProperty("postLiked");
        entity.addStringProperty("postFavorited");
        entity.addStringProperty("userName");
        entity.addStringProperty("userAvatar");
        entity.addStringProperty("userId");
        entity.addStringProperty("userFollowers");
        entity.addStringProperty("userFollowings");
        entity.addStringProperty("userPosts");

    }

    private void generatePlan(){
        Entity entity = schema.addEntity("Plan");
        entity.addStringProperty("id").unique();
        entity.addStringProperty("userId");
        entity.addStringProperty("quitDate");
        entity.addStringProperty("motivationsIds");
        entity.addStringProperty("triggersIds");
        entity.addStringProperty("cravingsIds");
        entity.addStringProperty("assistancesIds");
        entity.addStringProperty("pMotivationPhoto");
        entity.addStringProperty("pMotivationVideo");
        entity.addStringProperty("pMotivationAudio");
        entity.addStringProperty("relapsed");
        entity.addStringProperty("timestamp");
    }

    private void generateFollow(){
        Entity entity = schema.addEntity("Follow");
        entity.addStringProperty("followId").unique();
        entity.addStringProperty("followTimestamp");
        entity.addStringProperty("userId");
        entity.addStringProperty("userName");
        entity.addStringProperty("userEmail");
        entity.addStringProperty("userLanguage");
        entity.addStringProperty("userFollowings");
        entity.addStringProperty("userFollowers");
        entity.addStringProperty("userAvatar");
        entity.addStringProperty("userPosts");
        entity.addStringProperty("userFollowed");

    }


}
