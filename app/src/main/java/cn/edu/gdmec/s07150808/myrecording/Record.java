package cn.edu.gdmec.s07150808.myrecording;

/**
 * Created by chen on 2017.
 */
import java.util.Date;
import java.util.UUID;
public class Record {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    public Record(){
        //创建唯一标识符
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public UUID getId() { return mId; }
    public String getTitle() { return mTitle; }
    public void setTitle(String title) { mTitle = title; }
    public Date getDate() { return mDate; }
    public void setDate(Date date) { mDate = date; }
    public boolean isSolved() { return mSolved; }
    public void setSolved(boolean solved) { mSolved = solved; }

}
