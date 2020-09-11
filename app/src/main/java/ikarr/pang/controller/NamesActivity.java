package ikarr.pang.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ikarr.pang.model.PersonaAdapter;
import ikarr.pang.R;
import ikarr.pang.model.Persona;
import ikarr.pang.model.PersonaDbHelper;

public class NamesActivity extends AppCompatActivity {
    private final static String[] ancestriesList = Persona.getAncestriesList();
    private static String[] ancestriesFilter = new String[ancestriesList.length + 1];
    private static List<Persona> savedPersonas = MainActivity.initialSavedPersonas;
    private static List<Persona> filteredPersonas;

    PersonaDbHelper dbHelper = new PersonaDbHelper(this);

    public static boolean savePersona(Persona npc) {
        Persona current;
        for (int i = 0; i < savedPersonas.size(); i++ ) {
            current = savedPersonas.get(i);
            if (current.equals(npc)) {
                return false;
            }
        }
        savedPersonas.add(npc);
        return true;
    }
    public static void deletePersona(Persona npc) {
        savedPersonas.remove(npc);
        //TODO
    }

    public List<Persona> FilterByAncestry(int spinnerIndex) {
        if (spinnerIndex == 0) {
            return savedPersonas;
        }
        List<Persona> filteredByAncestry = new ArrayList<Persona>();
        String ancestry = Persona.getAncestriesList()[spinnerIndex - 1];
        Persona current;
        for (int i = 0; i < savedPersonas.size(); i++) {
            current = savedPersonas.get(i);
            if (current.getAncestry().equals(ancestry)) {
                filteredByAncestry.add(current);
            }
        }
        return filteredByAncestry;
    }

    private ImageView nLogoImg;
    private Spinner nFilterSpinner;
    private ListView nPersonaslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        // Get the saved list from memory
        //dbHelper = new PersonaDbHelper(this);
        //savedPersonas = dbHelper.initiateSavedPersonas(savedPersonas);
        //dbHelper.close();

        // Generate filter list from ancestries List
        for (int i = 0; i < ancestriesList.length; i++) {
            ancestriesFilter[i+1] = "Show " + ancestriesList[i] + "s";
        }
        ancestriesFilter[0] = "Show all characters";

        // Create spinner
        nFilterSpinner = (Spinner) findViewById(R.id.activity_names_filter_spinner);
        ArrayAdapter<String> filter = new ArrayAdapter<String>(this, R.layout.spinners_style, ancestriesFilter);
        filter.setDropDownViewResource(R.layout.spinners_style);
        nFilterSpinner.setAdapter(filter);

        // Logo click return to main
        nLogoImg = (ImageView) findViewById(R.id.activity_names_logo_img);
        nLogoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(NamesActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        // prepare saved characters List
        nPersonaslist = (ListView) findViewById(R.id.activity_names_characters_list);

        // Showing filtered list on spinner selection
        nFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int spinnerIndex = nFilterSpinner.getSelectedItemPosition();
                filteredPersonas = FilterByAncestry(spinnerIndex);
                nPersonaslist.setAdapter(new PersonaAdapter(getApplicationContext(), filteredPersonas));
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                int spinnerIndex = nFilterSpinner.getSelectedItemPosition();
                filteredPersonas = FilterByAncestry(spinnerIndex);
                nPersonaslist.setAdapter(new PersonaAdapter(getApplicationContext(), filteredPersonas));
            }
        });

        nPersonaslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Persona selectedPersona = filteredPersonas.get(position);
                String deleteText = selectedPersona.getFirstName() + " " + selectedPersona.getLastName() + " deleted.";
                Toast.makeText(getBaseContext(), deleteText, Toast.LENGTH_LONG).show();
                for (int i = 0; i < savedPersonas.size(); i++) {
                    if (selectedPersona.equals(savedPersonas.get(i))) {
                        savedPersonas.remove(i);
                    }
                }
                int spinnerIndex = nFilterSpinner.getSelectedItemPosition();
                filteredPersonas = FilterByAncestry(spinnerIndex);
                nPersonaslist.setAdapter(new PersonaAdapter(getApplicationContext(), filteredPersonas));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.emptyPersonaTable();
        dbHelper.insertPersonaList(savedPersonas);
        dbHelper.close();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}