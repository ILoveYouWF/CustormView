package com.example.a20161005.custormview.v7EditTextNum;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/5.
 */

public class EditTextShowNum extends LinearLayout {

    private LayoutInflater mInflater;
    private LinearLayout llView;
    private EditText etText;
    private TextView textNumber;
    private TextWatcher mWatcher;   //编辑控制器
    private String content;

    private int maxWord;
    private int levelOneNum;
    private int levelTwoNum;
    private int levtlOneColor;
    private int levelTwoColor;

    private float edittextSize;
    private int edittextColor;
    private float numtextSize;
    private int numtextMaxColor;
    private int numtextColor;
    private String edittexthint;

    public EditTextShowNum(Context context) {
        super(context);
    }

    public EditTextShowNum(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextNum);
        maxWord = typedArray.getInt(R.styleable.TextNum_maxWord, 500);
        edittextSize = typedArray.getFloat(R.styleable.TextNum_edittextSize, 15.0f);
        numtextSize = typedArray.getFloat(R.styleable.TextNum_numtextSize, 15.0f);
        edittextColor = typedArray.getInt(R.styleable.TextNum_edittextColor, 0x383838);
        numtextColor = typedArray.getInt(R.styleable.TextNum_numtextColor, 0xb3b3b3);
        numtextMaxColor = typedArray.getInt(R.styleable.TextNum_numtextMaxColor, 0xFF0000);
        edittexthint = typedArray.getString(R.styleable.TextNum_edittexthint);
        levelOneNum = typedArray.getInt(R.styleable.TextNum_levrlOneNum, 0);
        levelTwoNum = typedArray.getInt(R.styleable.TextNum_levelTwoNum, 0);
        levtlOneColor = typedArray.getInt(R.styleable.TextNum_levtlOneColor, 0xf89527);
        levelTwoColor = typedArray.getInt(R.styleable.TextNum_levelTwoColor, 0xfc6e42);
        if (levelOneNum != 0 && levelTwoNum != 0) {
            if (levelOneNum > levelTwoNum) {
                int num = levelTwoNum;
                levelTwoNum = levelOneNum;
                levelOneNum = num;
            }
        }
        mInflater = LayoutInflater.from(context);
        initXMLView();
    }

    private void initXMLView() {
        View view = mInflater.inflate(R.layout.edittext_num, this, false);
        llView = (LinearLayout) view.findViewById(R.id.ll_view);
        etText = (EditText) view.findViewById(R.id.et_text);
        etText.setTextSize(edittextSize);
        etText.setTextColor(edittextColor);
        textNumber = (TextView) view.findViewById(R.id.text_number);
        textNumber.setTextSize(numtextSize);
        textNumber.setTextColor(numtextColor);
        textNumber.setText("0/" + maxWord);
        if (edittexthint != null && edittexthint != "") {
            etText.setHint(edittexthint);
        }
        mWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.valueOf(s.length());
                if (number > maxWord) {
                    Toast.makeText(getContext(), "已到达最大字数！", Toast.LENGTH_SHORT).show();
                    etText.setText(content);
                    if (content != null && content != "") {
                        etText.setSelection(content.length());
                    }
                    textNumber.setText(Html.fromHtml("<font color='" + numtextMaxColor + "'>" + maxWord + "</font>" + "/" + maxWord));
                } else if (levelTwoNum != 0 && number > levelTwoNum) {
                    content = s.toString();
                    textNumber.setText(Html.fromHtml("<font color='" + levelTwoColor + "'>" + s.length() + "</font>" + "/" + maxWord));
                } else if (levelOneNum != 0 && number > levelOneNum) {
                    content = s.toString();
                    textNumber.setText(Html.fromHtml("<font color='" + levtlOneColor + "'>" + s.length() + "</font>" + "/" + maxWord));
                } else {
                    content = s.toString();
                    textNumber.setText(Html.fromHtml("<font color='" + numtextColor + "'>" + s.length() + "/" + maxWord + "</font>"));
                }
            }
        };
        etText.addTextChangedListener(mWatcher);
        this.addView(llView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EditText getEtText() {

        return etText;
    }

    public void setEtText(EditText etText) {
        this.etText = etText;
    }

    public TextView getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(TextView textNumber) {
        this.textNumber = textNumber;
    }

    public int getMaxWord() {
        return maxWord;
    }

    public void setMaxWord(int maxWord) {
        this.maxWord = maxWord;
    }

    public int getLevelOneNum() {
        return levelOneNum;
    }

    public void setLevelOneNum(int levelOneNum) {
        this.levelOneNum = levelOneNum;
    }

    public int getLevelTwoNum() {
        return levelTwoNum;
    }

    public void setLevelTwoNum(int levelTwoNum) {
        this.levelTwoNum = levelTwoNum;
    }

    public int getLevtlOneColor() {
        return levtlOneColor;
    }

    public void setLevtlOneColor(int levtlOneColor) {
        this.levtlOneColor = levtlOneColor;
    }

    public int getLevelTwoColor() {
        return levelTwoColor;
    }

    public void setLevelTwoColor(int levelTwoColor) {
        this.levelTwoColor = levelTwoColor;
    }

    public float getEdittextSize() {
        return edittextSize;
    }

    public void setEdittextSize(float edittextSize) {
        this.edittextSize = edittextSize;
    }

    public int getEdittextColor() {
        return edittextColor;
    }

    public void setEdittextColor(int edittextColor) {
        this.edittextColor = edittextColor;
    }

    public float getNumtextSize() {
        return numtextSize;
    }

    public void setNumtextSize(float numtextSize) {
        this.numtextSize = numtextSize;
    }

    public int getNumtextMaxColor() {
        return numtextMaxColor;
    }

    public void setNumtextMaxColor(int numtextMaxColor) {
        this.numtextMaxColor = numtextMaxColor;
    }

    public int getNumtextColor() {
        return numtextColor;
    }

    public void setNumtextColor(int numtextColor) {
        this.numtextColor = numtextColor;
    }

    public String getEdittexthint() {
        return edittexthint;
    }

    public void setEdittexthint(String edittexthint) {
        this.edittexthint = edittexthint;
    }
}
