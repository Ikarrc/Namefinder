package ikarr.pang.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ikarr.pang.R;

public class PersonaAdapter extends BaseAdapter {

    private Context context;
    private List<Persona> personaList;
    private LayoutInflater inflater;

    public PersonaAdapter(Context context, List<Persona> list) {
        this.context = context;
        this.personaList = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return personaList.size();
    }

    @Override
    public Persona getItem(int pos) {
        return personaList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.persona_list_display, null);

        // get info about Saved Characters
        Persona currentPersona = getItem(i);
        String currentFullName = currentPersona.getFirstName() + " " +currentPersona.getLastName();
        String currentDetails = currentPersona.getAncestry(); // + " " + currentPersona.getRole();
        int currentGender = currentPersona.getGender();
        //String currentRole = currentPersona.getRole();

        // set texts in list
        TextView fullNameView = view.findViewById(R.id.character_list_name);
        fullNameView.setText(currentFullName);

        TextView detailsView = view.findViewById(R.id.character_list_details);
        detailsView.setText(currentDetails);

        // set gender icon in list
        ImageView genderView = view.findViewById(R.id.character_list_gender);
        String gender = "genderless";
        if (currentGender == 1) {
            gender = "female";
        }
        if (currentGender == 2) {
            gender = "male";
        }
        String iconName = gender + "_icon_color";
        int iconId = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        genderView.setImageResource(iconId);

        // set portrait in list
        /* ImageView roleView = view.findViewById(R.id.character_list_portrait);
        // TODO : find default image for each class

        String imageRole = currentRole + "_default_portrait";
        int resId = context.getResources().getIdentifier(imageRole, "drawable", context.getPackageName());
        roleView.setImageResource(resId); */

        TextView deleteView = view.findViewById(R.id.character_list_delete);
        deleteView.setText("x");

        return view;
    }
}
