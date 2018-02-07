package com.clearsoft.welivre.domain.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.clearsoft.welivre.domain.entities.Plan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLAN".
*/
public class PlanDao extends AbstractDao<Plan, Void> {

    public static final String TABLENAME = "PLAN";

    /**
     * Properties of entity Plan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", false, "ID");
        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID");
        public final static Property QuitDate = new Property(2, String.class, "quitDate", false, "QUIT_DATE");
        public final static Property MotivationsIds = new Property(3, String.class, "motivationsIds", false, "MOTIVATIONS_IDS");
        public final static Property TriggersIds = new Property(4, String.class, "triggersIds", false, "TRIGGERS_IDS");
        public final static Property CravingsIds = new Property(5, String.class, "cravingsIds", false, "CRAVINGS_IDS");
        public final static Property AssistancesIds = new Property(6, String.class, "assistancesIds", false, "ASSISTANCES_IDS");
        public final static Property PMotivationPhoto = new Property(7, String.class, "pMotivationPhoto", false, "P_MOTIVATION_PHOTO");
        public final static Property PMotivationVideo = new Property(8, String.class, "pMotivationVideo", false, "P_MOTIVATION_VIDEO");
        public final static Property PMotivationAudio = new Property(9, String.class, "pMotivationAudio", false, "P_MOTIVATION_AUDIO");
        public final static Property Relapsed = new Property(10, String.class, "relapsed", false, "RELAPSED");
        public final static Property Timestamp = new Property(11, String.class, "timestamp", false, "TIMESTAMP");
    };


    public PlanDao(DaoConfig config) {
        super(config);
    }
    
    public PlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLAN\" (" + //
                "\"ID\" TEXT UNIQUE ," + // 0: id
                "\"USER_ID\" TEXT," + // 1: userId
                "\"QUIT_DATE\" TEXT," + // 2: quitDate
                "\"MOTIVATIONS_IDS\" TEXT," + // 3: motivationsIds
                "\"TRIGGERS_IDS\" TEXT," + // 4: triggersIds
                "\"CRAVINGS_IDS\" TEXT," + // 5: cravingsIds
                "\"ASSISTANCES_IDS\" TEXT," + // 6: assistancesIds
                "\"P_MOTIVATION_PHOTO\" TEXT," + // 7: pMotivationPhoto
                "\"P_MOTIVATION_VIDEO\" TEXT," + // 8: pMotivationVideo
                "\"P_MOTIVATION_AUDIO\" TEXT," + // 9: pMotivationAudio
                "\"RELAPSED\" TEXT," + // 10: relapsed
                "\"TIMESTAMP\" TEXT);"); // 11: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Plan entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String quitDate = entity.getQuitDate();
        if (quitDate != null) {
            stmt.bindString(3, quitDate);
        }
 
        String motivationsIds = entity.getMotivationsIds();
        if (motivationsIds != null) {
            stmt.bindString(4, motivationsIds);
        }
 
        String triggersIds = entity.getTriggersIds();
        if (triggersIds != null) {
            stmt.bindString(5, triggersIds);
        }
 
        String cravingsIds = entity.getCravingsIds();
        if (cravingsIds != null) {
            stmt.bindString(6, cravingsIds);
        }
 
        String assistancesIds = entity.getAssistancesIds();
        if (assistancesIds != null) {
            stmt.bindString(7, assistancesIds);
        }
 
        String pMotivationPhoto = entity.getPMotivationPhoto();
        if (pMotivationPhoto != null) {
            stmt.bindString(8, pMotivationPhoto);
        }
 
        String pMotivationVideo = entity.getPMotivationVideo();
        if (pMotivationVideo != null) {
            stmt.bindString(9, pMotivationVideo);
        }
 
        String pMotivationAudio = entity.getPMotivationAudio();
        if (pMotivationAudio != null) {
            stmt.bindString(10, pMotivationAudio);
        }
 
        String relapsed = entity.getRelapsed();
        if (relapsed != null) {
            stmt.bindString(11, relapsed);
        }
 
        String timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindString(12, timestamp);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Plan readEntity(Cursor cursor, int offset) {
        Plan entity = new Plan( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // quitDate
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // motivationsIds
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // triggersIds
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cravingsIds
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // assistancesIds
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // pMotivationPhoto
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pMotivationVideo
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // pMotivationAudio
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // relapsed
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Plan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setQuitDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMotivationsIds(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTriggersIds(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCravingsIds(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAssistancesIds(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPMotivationPhoto(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPMotivationVideo(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPMotivationAudio(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRelapsed(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTimestamp(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Plan entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Plan entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
