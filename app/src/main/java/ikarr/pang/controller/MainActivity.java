package ikarr.pang.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ikarr.pang.R;
import ikarr.pang.model.Persona;
import ikarr.pang.model.GenderLocks;
import ikarr.pang.model.NameLock;
import ikarr.pang.model.PersonaDbHelper;
import ikarr.pang.model.RandomName;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String[] ancestriesList = Persona.getAncestriesList();

    private Spinner mAncestriesSpinner;
    private ImageButton mGenderlessBtn;
    private ImageButton mFemaleBtn;
    private ImageButton mMaleBtn;
    private ImageView mFirstNameLockBtn;
    private TextView mFirstNameTxt;
    private TextView mLastNameTxt;
    private ImageView mLastNameLockBtn;
    private ImageView mDiceBtn;
    private ImageView mListBtn;
    private ImageView mRecordBtn;

    public static List<Persona> initialSavedPersonas;

    public static NameLock firstNameLock = new NameLock(0);
    public static NameLock lastNameLock = new NameLock(1);

    public static GenderLocks genderIcons = new GenderLocks();

    public static Persona workingNPC = new Persona(0, Persona.randomAncestry());
    public static RandomName startName = new RandomName(workingNPC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAncestriesSpinner = (Spinner) findViewById(R.id.activity_main_ancestries_spinner);
        mGenderlessBtn = (ImageButton) findViewById(R.id.activity_main_genderless_button);
        mFemaleBtn = (ImageButton) findViewById(R.id.activity_main_female_button);
        mMaleBtn = (ImageButton) findViewById(R.id.activity_main_male_button);
        mFirstNameLockBtn = (ImageView) findViewById(R.id.activity_main_left_lock_button);
        mFirstNameTxt = (TextView) findViewById(R.id.activity_main_first_name_text);
        mLastNameTxt = (TextView) findViewById(R.id.activity_main_last_name_text);
        mLastNameLockBtn = (ImageView) findViewById(R.id.activity_main_right_lock_button);
        mListBtn = (ImageView) findViewById(R.id.activity_main_list_button);
        mDiceBtn = (ImageView) findViewById(R.id.activity_main_dice_button);
        mRecordBtn = (ImageView) findViewById(R.id.activity_main_record_button);

        PersonaDbHelper dbHelper = new PersonaDbHelper(this);
        initialSavedPersonas = dbHelper.initiateSavedPersonas();

        //ArrayAdapter<CharSequence> ancestriesChoice = ArrayAdapter.createFromResource(this, R.array.filter_list, R.layout.spinners_style);
        ArrayAdapter<String> ancestriesChoice = new ArrayAdapter<String>(this, R.layout.spinners_style, ancestriesList);
        ancestriesChoice.setDropDownViewResource(R.layout.spinners_style);
        mAncestriesSpinner.setAdapter(ancestriesChoice);

        // Showing registered locks states even if page change
        if (firstNameLock.isOpen()) {
            mFirstNameLockBtn.setImageResource(R.drawable.open_lock_icon);
        } else {
            mFirstNameLockBtn.setImageResource(R.drawable.closed_lock_icon);
        }
        if (lastNameLock.isOpen()) {
            mLastNameLockBtn.setImageResource(R.drawable.open_lock_icon);
        } else {
            mLastNameLockBtn.setImageResource(R.drawable.closed_lock_icon);
        }
        // Selecting Gender state after page change
        int selectedGender = genderIcons.getSelectedGender();
        if (selectedGender < 0) {
            selectedGender = 0;
        }
        switch (selectedGender) {
            case 0:
                mGenderlessBtn.setImageResource(R.drawable.genderless_icon_color);
                break;
            case 1:
                mFemaleBtn.setImageResource(R.drawable.female_icon_color);
                break;
            case 2:
                mMaleBtn.setImageResource(R.drawable.male_icon_color);
                break;
        }
        // Showing names after page change
        mFirstNameTxt.setText(workingNPC.getFirstName());
        mLastNameTxt.setText(workingNPC.getLastName());

        // Ancestry Spinner positioning
        int ancestryIndex = workingNPC.getAncestryIndex();
        mAncestriesSpinner.setSelection(ancestryIndex);

        /* ************* All clickable buttons ************** */
        // Gender choice
        mGenderlessBtn.setTag(0);
        mFemaleBtn.setTag(1);
        mMaleBtn.setTag(2);
        // Locks
        mFirstNameLockBtn.setTag(3);
        mLastNameLockBtn.setTag(4);
        // Go to List
        mListBtn.setTag(5);
        // Random name
        mDiceBtn.setTag(6);
        // Record current name
        mRecordBtn.setTag(7);

        // Click preparation
        mGenderlessBtn.setOnClickListener(this);
        mFemaleBtn.setOnClickListener(this);
        mMaleBtn.setOnClickListener(this);
        mFirstNameLockBtn.setOnClickListener(this);
        mLastNameLockBtn.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
        mDiceBtn.setOnClickListener(this);
        mRecordBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        switch (tag) {
            case 0:
                genderIcons.selectGender(0);
                mGenderlessBtn.setImageResource(R.drawable.genderless_icon_color);
                mFemaleBtn.setImageResource(R.drawable.female_icon_grey);
                mMaleBtn.setImageResource(R.drawable.male_icon_grey);
                break;
            case 1:
                genderIcons.selectGender(1);
                mGenderlessBtn.setImageResource(R.drawable.genderless_icon_grey);
                mFemaleBtn.setImageResource(R.drawable.female_icon_color);
                mMaleBtn.setImageResource(R.drawable.male_icon_grey);
                break;
            case 2:
                genderIcons.selectGender(2);
                mGenderlessBtn.setImageResource(R.drawable.genderless_icon_grey);
                mFemaleBtn.setImageResource(R.drawable.female_icon_grey);
                mMaleBtn.setImageResource(R.drawable.male_icon_color);
                break;
            case 3:
                firstNameLock.switchLock();
                if (firstNameLock.isOpen()) {
                    mFirstNameLockBtn.setImageResource(R.drawable.open_lock_icon);
                } else {
                    mFirstNameLockBtn.setImageResource(R.drawable.closed_lock_icon);
                }
                break;
            case 4:
                lastNameLock.switchLock();
                if (lastNameLock.isOpen()) {
                    mLastNameLockBtn.setImageResource(R.drawable.open_lock_icon);
                } else {
                    mLastNameLockBtn.setImageResource(R.drawable.closed_lock_icon);
                }
                break;
            case 5:
                Intent namesActivity = new Intent(MainActivity.this, NamesActivity.class);
                startActivity(namesActivity);
                break;
            case 6:
                String ancestry = mAncestriesSpinner.getSelectedItem().toString();
                int gender = genderIcons.getSelectedGender();
                workingNPC.setGender(gender);
                workingNPC.setAncestry(ancestry);
                workingNPC.setFirstName(mFirstNameTxt.getText().toString());
                workingNPC.setLastName(mLastNameTxt.getText().toString());
                RandomName name = new RandomName(workingNPC);
                mFirstNameTxt.setText(workingNPC.getFirstName());
                mLastNameTxt.setText(workingNPC.getLastName());
                break;
            case 7:
                Persona newOne = new Persona(
                        genderIcons.getSelectedGender(),
                        mAncestriesSpinner.getSelectedItem().toString(),
                        mFirstNameTxt.getText().toString(),
                        mLastNameTxt.getText().toString()
                );
                NamesActivity.savePersona(newOne);
                Toast.makeText(this, newOne.getFirstName() + " " + newOne.getLastName() + " is saved.", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}