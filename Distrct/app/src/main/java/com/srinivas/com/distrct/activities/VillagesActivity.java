package com.srinivas.com.distrct.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.srinivas.com.distrct.R;
import com.srinivas.com.distrct.adapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VillagesActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villages);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        backbutton = (ImageView) findViewById(R.id.imageView);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Revally");
        listDataHeader.add("Gopalpet");
        listDataHeader.add("Peddamandaddi");
        listDataHeader.add("Ghanpur");
        listDataHeader.add("Pangal");
        listDataHeader.add("Chinnambavi");
        listDataHeader.add("Weepangandla");
        listDataHeader.add("Srirangapur");
        listDataHeader.add("Pebbair");
        listDataHeader.add("Madanapur");
        listDataHeader.add("Kothakota");
        listDataHeader.add("Amarchintha");

        List<String> Revally = new ArrayList<String>();
        Revally.add("1 Yedula ");
        Revally.add("2 Bandaraipakula ");
        Revally.add("3 Chennaram ");
        Revally.add("4 Kesampeta ");
        Revally.add("5 Shanaipalle ");
        Revally.add("6 Thalpunur ");
        Revally.add("7 Cheerkapalle ");
        Revally.add("8 Nagapur ");
        Revally.add("9 Revally ");
        Revally.add("10 Vallabhanpalle ");
        Revally.add("11 Konkalapalle");

        List<String> Gopalpet = new ArrayList<String>();
        Gopalpet.add("1 Thadparthy");
        Gopalpet.add("2 Chennur");
        Gopalpet.add("3 Buddharam");
        Gopalpet.add("4 Polkepahad");
        Gopalpet.add("5 Chakalpalle");
        Gopalpet.add("6 Gopalpeta ");
        Gopalpet.add("7 Munnanur");
        Gopalpet.add("8 JainThirumalapur");
        Gopalpet.add("9 Yedutla");


        List<String> Peddamandaddi = new ArrayList<String>();
        Peddamandaddi.add("1 Peddamandaddi");
        Peddamandaddi.add("2 Manigilla");
        Peddamandaddi.add("3 Alwal");
        Peddamandaddi.add("4 Chinnamandadi");
        Peddamandaddi.add("5 Jagathpalle");
        Peddamandaddi.add("6 Mojerla");
        Peddamandaddi.add("7 Gatlakhanapur");
        Peddamandaddi.add("8 Maddigatla");
        Peddamandaddi.add("9 Veeraipalle");
        Peddamandaddi.add("10 Pamireddipalle");
        Peddamandaddi.add("11 Veltoor");
        Peddamandaddi.add("12 Balijapalle");
        Peddamandaddi.add("13 Jangamaipalle");

        List<String> Ghanpur = new ArrayList<String>();
        Ghanpur.add("1 Ghanpur");
        Ghanpur.add("2Almaipalle");
        Ghanpur.add("3 Malkimianpalle");
        Ghanpur.add("4 Venkatampalle");
        Ghanpur.add("5 Agaram");
        Ghanpur.add("6 Parwathapur");
        Ghanpur.add("7 Shapur");
        Ghanpur.add("8 Kamaluddinpur");
        Ghanpur.add("9 Thirumalaipalle");
        Ghanpur.add("10 Appareddipalle");
        Ghanpur.add("11  Anthaipalle");
        Ghanpur.add("12 Manajipet");
        Ghanpur.add("13 Solipur");
        Ghanpur.add("14 Salkalapur");
        Ghanpur.add("15  Upparapalle");
        Ghanpur.add("16 Mamidimada");
        Ghanpur.add("17 Malkapur");
        Ghanpur.add("18 Suraipalle");


        List<String> Pangal = new ArrayList<String>();
        Pangal.add("1 Pangal");
        Pangal.add("2 Gopalpu");
        Pangal.add("3 Bandapalle");
        Pangal.add("4 Annaram");
        Pangal.add("5 Madhavaraopalle");
        Pangal.add("6 Vengalaipalle");
        Pangal.add("7 Kistapur");
        Pangal.add("8 Mahammadapur");
        Pangal.add("9 Jammapur");
        Pangal.add("10 Nizamabad");
        Pangal.add("11 Sakhapur");
        Pangal.add("12 Davajipalle");
        Pangal.add("13  Remaddula");
        Pangal.add("14 Kethepalle");
        Pangal.add("15 Chintakunta");
        Pangal.add("16 Dondaipalle");
        Pangal.add("17 Mallaipalle");
        Pangal.add("18 Chikkepalle");
        Pangal.add("19 Rayanipalle");
        Pangal.add("20 Busireddipalle");
        Pangal.add("21  Thellarallapalle");
        Pangal.add("22  Kadirepad");


        List<String> Chinnambavi = new ArrayList<String>();
        Chinnambavi.add("1 Dagada");
        Chinnambavi.add("2 Peddamarur ");
        Chinnambavi.add("3 Chinnamarur");
        Chinnambavi.add("4  Vellatur  ");
        Chinnambavi.add("5  Chellepahad ");
        Chinnambavi.add("6 Ayyavaripalle");
        Chinnambavi.add("7  Kalloor");
        Chinnambavi.add("8  Koppunur");
        Chinnambavi.add("9 Lakshmipalle");
        Chinnambavi.add("10 Solipuram");
        Chinnambavi.add("11  Ammaipalle");
        Chinnambavi.add("12 Dagadapalle");
        Chinnambavi.add("13 Velgonda");
        Chinnambavi.add("14  Miyapuram");
        Chinnambavi.add("15  Bekkam");
        Chinnambavi.add("16  Gaddabaswapuram");

        List<String> Weepangandla = new ArrayList<String>();
        Weepangandla.add("1 Weepangandla");
        Weepangandla.add("2 Sanginepalle");
        Weepangandla.add("3  Govardhangiri");
        Weepangandla.add("4 Toomkunta ");
        Weepangandla.add("5  Sampatraopalle");
        Weepangandla.add("6 Pulgarcherla ");
        Weepangandla.add("7  Kalvarala");
        Weepangandla.add("8  Gopaldinne");
        Weepangandla.add("9  Korlakunta");
        Weepangandla.add("10  Bollaram");
        Weepangandla.add("11  Vallabhapur");


        List<String> Srirangapur = new ArrayList<String>();
        Srirangapur.add("1 Srirangapur  ");
        Srirangapur.add("2 Thatipamula");
        Srirangapur.add("3 Nagarala ");
        Srirangapur.add("4 Kamballapur ");
        Srirangapur.add("5 Venkatapur (");
        Srirangapur.add("6  Nagasanipalle ");
        Srirangapur.add("7. Janampeta");


        List<String> Pebbair = new ArrayList<String>();
        Pebbair.add("1 Pebbair");
        Pebbair.add("2 Chelimilla");
        Pebbair.add("3 Thomalapalle");
        Pebbair.add("4 Kanchiraopalle");
        Pebbair.add("5 Shakhapur");
        Pebbair.add("6 Rangapur");
        Pebbair.add("7 Ramapur");
        Pebbair.add("8 Bunyadpur ");
        Pebbair.add("9 Pathapalle");
        Pebbair.add("10 Janumpalle");
        Pebbair.add("11 Ramammapeta");
        Pebbair.add("12 Ayyavaripalle");
        Pebbair.add("13 Sugur");
        Pebbair.add("14 Munagamanudinne");
        Pebbair.add("15 Penchikalpadu");
        Pebbair.add("16 Erladinne");
        Pebbair.add("17 Gummadam");
        Pebbair.add("18Burdipadu");
        Pebbair.add("19 Thippaipalle");
        Pebbair.add("20 Yaparla");


        List<String> Madanapur = new ArrayList<String>();
        Madanapur.add("1.Madanapur");
        Madanapur.add("2 Govindahalli");
        Madanapur.add("3  Dantanoor");
        Madanapur.add("4  Shankarampeta");
        Madanapur.add("5  Thirumalaipalle");
        Madanapur.add("6	Ramanpadu");
        Madanapur.add("7  Ajjakollu");
        Madanapur.add("8  Narsingapur");
        Madanapur.add("9  Konnur");
        Madanapur.add("10 Dwarakanagar");
        Madanapur.add("11 Nelividi");
        Madanapur.add("12  Duppalle");
        Madanapur.add("13Kothapalle");
        Madanapur.add("15   Karvena");


        List<String> Kothakota = new ArrayList<String>();
        Kothakota.add("1 Kothakota");
        Kothakota.add("2 Palem");
        Kothakota.add("3 Kanaipalle");
        Kothakota.add("4 Nirven");
        Kothakota.add("5 Amadabakula");
        Kothakota.add("6 Kanimetta");
        Kothakota.add("7 Sankireddipalle");
        Kothakota.add("8 Ramnathpur");
        Kothakota.add("9 Rayanpet");
        Kothakota.add("10 Satyahalli");
        Kothakota.add("11 Mummallapalle");
        Kothakota.add("12 Pathajangamaipalle");
        Kothakota.add("13 Boothpur");
        Kothakota.add("14 Pullareddikunta");
        Kothakota.add("15 Miraspalle");
        Kothakota.add("16 Natavalli");
        Kothakota.add("17 Nancharammapet");
        Kothakota.add("18 Apparala");
        Kothakota.add("19 Pamapur");
        Kothakota.add("20 Ramakrishnapur");
        Kothakota.add("21 Waddawata");
        Kothakota.add("22 Cherlapalle1");


        List<String> Amarchintha = new ArrayList<String>();
        Amarchintha.add("1 Amarchintha");
        Amarchintha.add("2 Mastipur");
        Amarchintha.add("3 Pamireddipalle");
        Amarchintha.add("4 Kankanvanipalle");
        Amarchintha.add("5 Singampeta");
        Amarchintha.add("6 Nandimalla");
        Amarchintha.add("7 Chinthareddipalle");
        Amarchintha.add("8 Mittanandimalla");
        Amarchintha.add("9 Erladinne");
        Amarchintha.add("10 Chandraghad");
        Amarchintha.add("11 Dharmapur");
        Amarchintha.add("12 Nagalkadumur");
        Amarchintha.add("13 Kistampalle");

        listDataChild.put(listDataHeader.get(0), Revally); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Gopalpet);
        listDataChild.put(listDataHeader.get(2), Peddamandaddi);
        listDataChild.put(listDataHeader.get(1), Ghanpur);
        listDataChild.put(listDataHeader.get(2), Pangal);
        listDataChild.put(listDataHeader.get(1), Chinnambavi);
        listDataChild.put(listDataHeader.get(2), Weepangandla);
        listDataChild.put(listDataHeader.get(1), Srirangapur);
        listDataChild.put(listDataHeader.get(2), Pebbair);
        listDataChild.put(listDataHeader.get(1), Madanapur);
        listDataChild.put(listDataHeader.get(2), Kothakota);


    }


}
