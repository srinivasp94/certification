package com.android.Uga.Pojo;

import android.widget.EditText;

import com.android.Uga.R;

import java.util.ArrayList;

/**
 * Created by New android on 29-06-2016.
 */
public class Choose_diet_pojo {
    String item_name;
    double energy_weight;
    double fat_weight;
    double fiber_wieght;
    double protien_weight;
    double Ca_wt;
    double AvP_wt;
    double Sodium_wt;
    double Methionine_wt;
    String et;
    double energy_lbs;
    int image;
    boolean textclear = true;
    public double getEnergy_lbs() {
        return energy_weight*2.20462;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int    id;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    double Lysine;
    String title;
    String mintitle;
    double quntity;
    int position;
    public double getQuntity() {
        return quntity;
    }

    public void setQuntity(double quntity) {
        this.quntity = quntity;
    }

    public double getEnergy_weight() {
        return energy_weight;
    }

    public void setEnergy_weight(double energy_weight) {
        this.energy_weight = energy_weight;
    }

    public double getFat_weight() {
        return fat_weight;
    }

    public void setFat_weight(double fat_weight) {
        this.fat_weight = fat_weight;
    }

    public double getFiber_wieght() {
        return fiber_wieght;
    }

    public void setFiber_wieght(double fiber_wieght) {
        this.fiber_wieght = fiber_wieght;
    }

    public double getProtien_weight() {
        return protien_weight;
    }

    public void setProtien_weight(double protien_weight) {
        this.protien_weight = protien_weight;
    }

    public double getCa_wt() {
        return Ca_wt;
    }

    public void setCa_wt(double ca_wt) {
        Ca_wt = ca_wt;
    }

    public double getAvP_wt() {
        return AvP_wt;
    }

    public void setAvP_wt(double avP_wt) {
        AvP_wt = avP_wt;
    }

    public double getSodium_wt() {
        return Sodium_wt;
    }

    public void setSodium_wt(double sodium_wt) {
        Sodium_wt = sodium_wt;
    }

    public double getMethionine_wt() {
        return Methionine_wt;
    }

    public void setMethionine_wt(double methionine_wt) {
        Methionine_wt = methionine_wt;
    }

    public double getLysine() {
        return Lysine;
    }

    public void setLysine(double lysine) {
        Lysine = lysine;
    }


    public String getMintitle() {
        return mintitle;
    }

    public void setMintitle(String mintitle) {
        this.mintitle = mintitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isTextclear() {
        return textclear;
    }

    public void setTextclear(boolean textclear) {
        this.textclear = textclear;
    }

    public static ArrayList<Choose_diet_pojo> feedData() {
        ArrayList<Choose_diet_pojo> list = new ArrayList<Choose_diet_pojo>();

        //maize
        Choose_diet_pojo maize = new Choose_diet_pojo();
        maize.setTitle("Maize");
        maize.setMintitle("Maize");
        maize.setEnergy_weight(3373);
        maize.setFat_weight(3.5);
        maize.setFiber_wieght(1.9);
        maize.setProtien_weight(7.5);
        maize.setAvP_wt(0.12);
        maize.setCa_wt(0.01);
        maize.setLysine(0.24);
        maize.setMethionine_wt(0.18);
        maize.setSodium_wt(0.02);
        maize.setId(15);
        maize.setQuntity(1);
        maize.setImage(R.drawable.maize);
        maize.setEt("1.0");
        list.add(maize);

        //wheat Bran
        Choose_diet_pojo wheat = new Choose_diet_pojo();
        wheat.setTitle("Wheat Bran");
        wheat.setMintitle("W.Bran");
        wheat.setEnergy_weight(1300);
        wheat.setFat_weight(4.0);
        wheat.setFiber_wieght(10);
        wheat.setProtien_weight(14.8);
        wheat.setAvP_wt(0.38);
        wheat.setCa_wt(0.14);
        wheat.setLysine(0.6);
        wheat.setMethionine_wt(0.2);
        wheat.setSodium_wt(0.06);
        wheat.setId(27);
        wheat.setQuntity(1);
        wheat.setImage(R.drawable.wheat);
        wheat.setEt("1.0");
        list.add(wheat);


        //Rice Bran
        Choose_diet_pojo Rice = new Choose_diet_pojo();
        Rice.setTitle("Rice Bran");
        Rice.setMintitle("R.Bran");
        Rice.setEnergy_weight(2040);
        Rice.setFat_weight(5.9);
        Rice.setFiber_wieght(13);
        Rice.setProtien_weight(13.5);
        Rice.setAvP_wt(0.24);
        Rice.setCa_wt(0.1);
        Rice.setLysine(0.5);
        Rice.setMethionine_wt(0.17);
        Rice.setSodium_wt(0.1);
        Rice.setId(22);
        Rice.setQuntity(1);
        Rice.setImage(R.drawable.rice);
        Rice.setEt("1.0");
        list.add(Rice);

        //Palm Kernal Meal
        Choose_diet_pojo palm_kernal = new Choose_diet_pojo();
        palm_kernal.setMintitle("Palm Kernal");
        palm_kernal.setTitle("Palm Kernal Meal");
        palm_kernal.setEnergy_weight(2100);
        palm_kernal.setFat_weight(6);
        palm_kernal.setFiber_wieght(12);
        palm_kernal.setProtien_weight(18);
        palm_kernal.setAvP_wt(0.15);
        palm_kernal.setCa_wt(0.21);
        palm_kernal.setLysine(0.65);
        palm_kernal.setMethionine_wt(0.3);
        palm_kernal.setSodium_wt(0);
        palm_kernal.setId(19);
        palm_kernal.setQuntity(1);
        palm_kernal.setImage(R.drawable.palm_kernal_meal);
        palm_kernal.setEt("1.0");
        list.add(palm_kernal);

        //Soyabean Meal (44)
        Choose_diet_pojo Soya = new Choose_diet_pojo();
        Soya.setTitle("Soyabean Meal(44)");
        Soya.setMintitle("Soya");
        Soya.setEnergy_weight(2420);
        Soya.setFat_weight(3.5);
        Soya.setFiber_wieght(6.5);
        Soya.setProtien_weight(42);
        Soya.setAvP_wt(0.2);
        Soya.setCa_wt(0.2);
        Soya.setLysine(2.7);
        Soya.setMethionine_wt(0.6);
        Soya.setSodium_wt(0.04);
        Soya.setId(24);
        Soya.setQuntity(1);
        Soya.setImage(R.drawable.soya);
        Soya.setEt("1.0");
        list.add(Soya);

        //Full-fat Soya (38)
        Choose_diet_pojo full_fat_soya = new Choose_diet_pojo();
        full_fat_soya.setMintitle("Full-fat Soya");
        full_fat_soya.setTitle("Full-fat Soya (38)");
        full_fat_soya.setEnergy_weight(3350);
        full_fat_soya.setFat_weight(18);
        full_fat_soya.setFiber_wieght(5);
        full_fat_soya.setProtien_weight(38);
        full_fat_soya.setAvP_wt(0.2);
        full_fat_soya.setCa_wt(0.25);
        full_fat_soya.setLysine(2.4);
        full_fat_soya.setMethionine_wt(0.54);
        full_fat_soya.setSodium_wt(0.04);
        full_fat_soya.setId(10);
        full_fat_soya.setQuntity(1);
        full_fat_soya.setImage(R.drawable.full_fat_soya);
        full_fat_soya.setEt("1.0");
        list.add(full_fat_soya);

        //Cottonseed Meal
        Choose_diet_pojo Cottonseed = new Choose_diet_pojo();
        Cottonseed.setTitle("Cottonseed Meal");
        Cottonseed.setMintitle("Cotton");
        Cottonseed.setEnergy_weight(2100);
        Cottonseed.setFat_weight(3.9);
        Cottonseed.setFiber_wieght(12.6);
        Cottonseed.setProtien_weight(41.0);
        Cottonseed.setAvP_wt(0.17);
        Cottonseed.setCa_wt(0.32);
        Cottonseed.setLysine(1.52);
        Cottonseed.setMethionine_wt(0.55);
        Cottonseed.setSodium_wt(0.04);
        Cottonseed.setQuntity(1);
        Cottonseed.setId(5);
        Cottonseed.setQuntity(1);
        Cottonseed.setImage(R.drawable.cottenseed);
        Cottonseed.setEt("1.0");
        list.add(Cottonseed);

        //Fish Meal
        Choose_diet_pojo Fish = new Choose_diet_pojo();
        Fish.setTitle("Fish Meal");
        Fish.setMintitle("Fish");
        Fish.setEnergy_weight(2600);
        Fish.setFat_weight(4.0);
        Fish.setFiber_wieght(1.0);
        Fish.setProtien_weight(61);
        Fish.setAvP_wt(3.5);
        Fish.setCa_wt(7.0);
        Fish.setLysine(4.3);
        Fish.setMethionine_wt(1.65);
        Fish.setSodium_wt(0.97);
        Fish.setId(9);
        Fish.setQuntity(1);
        Fish.setImage(R.drawable.fishmeal);
        Fish.setEt("1.0");
        list.add(Fish);

        //Meat & Bone Meal
        Choose_diet_pojo meat_bone = new Choose_diet_pojo();
        meat_bone.setMintitle("Meat & Bone");
        meat_bone.setTitle("Meat & Bone Meal");
        meat_bone.setEnergy_weight(2302);
        meat_bone.setFat_weight(10.8);
        meat_bone.setFiber_wieght(3);
        meat_bone.setProtien_weight(47);
        meat_bone.setAvP_wt(4.89);
        meat_bone.setCa_wt(10.5);
        meat_bone.setLysine(2.03);
        meat_bone.setMethionine_wt(0.55);
        meat_bone.setSodium_wt(0.72);
        meat_bone.setId(16);
        meat_bone.setQuntity(1);
        meat_bone.setImage(R.drawable.meat_bone_meal);
        meat_bone.setEt("1.0");
        list.add(meat_bone);

        //Groundnut Meal
        Choose_diet_pojo groundnut_meal = new Choose_diet_pojo();
        groundnut_meal.setMintitle("Groundnut");
        groundnut_meal.setTitle("Groundnut Meal");
        groundnut_meal.setEnergy_weight(2500);
        groundnut_meal.setFat_weight(7.3);
        groundnut_meal.setFiber_wieght(10);
        groundnut_meal.setProtien_weight(42);
        groundnut_meal.setAvP_wt(0.18);
        groundnut_meal.setCa_wt(0.15);
        groundnut_meal.setLysine(1.55);
        groundnut_meal.setMethionine_wt(0.41);
        groundnut_meal.setSodium_wt(0.1);
        groundnut_meal.setId(11);
        groundnut_meal.setQuntity(1);
        groundnut_meal.setImage(R.drawable.groundnut_meal);
        groundnut_meal.setEt("1.0");
        list.add(groundnut_meal);

        //Cassava Tubers Meal
        Choose_diet_pojo cassava_tubers = new Choose_diet_pojo();
        cassava_tubers.setMintitle("Cassava Tubers");
        cassava_tubers.setTitle("Cassava Tubers Meal");
        cassava_tubers.setEnergy_weight(2915);
        cassava_tubers.setFat_weight(0.3);
        cassava_tubers.setFiber_wieght(7.6);
        cassava_tubers.setProtien_weight(2.4);
        cassava_tubers.setAvP_wt(0);
        cassava_tubers.setCa_wt(0.15);
        cassava_tubers.setLysine(0);
        cassava_tubers.setMethionine_wt(0);
        cassava_tubers.setSodium_wt(0);
        cassava_tubers.setId(2);
        cassava_tubers.setQuntity(1);
        cassava_tubers.setImage(R.drawable.cassava_tubers_meal);
        cassava_tubers.setEt("1.0");
        list.add(cassava_tubers);


        //Sunflower Meal
        Choose_diet_pojo sunflower = new Choose_diet_pojo();
        sunflower.setMintitle("Sunflower");
        sunflower.setTitle("Sunflower Meal");
        sunflower.setEnergy_weight(1834);
        sunflower.setFat_weight(9.32);
        sunflower.setFiber_wieght(18.84);
        sunflower.setProtien_weight(27.9);
        sunflower.setAvP_wt(0.14);
        sunflower.setCa_wt(0.32);
        sunflower.setLysine(0.99);
        sunflower.setMethionine_wt(0.63);
        sunflower.setSodium_wt(0);
        sunflower.setId(26);
        sunflower.setQuntity(1);
        sunflower.setImage(R.drawable.sunflower_meal);
        sunflower.setEt("1.0");
        list.add(sunflower);


        //Coconut Meal
        Choose_diet_pojo coconut_meal = new Choose_diet_pojo();
        coconut_meal.setMintitle("Coconut");
        coconut_meal.setTitle("Coconut Meal");
        coconut_meal.setEnergy_weight(1791);
        coconut_meal.setFat_weight(9.21);
        coconut_meal.setFiber_wieght(10.5);
        coconut_meal.setProtien_weight(18.5);
        coconut_meal.setAvP_wt(0.22);
        coconut_meal.setCa_wt(0.08);
        coconut_meal.setLysine(0.47);
        coconut_meal.setMethionine_wt(0.24);
        coconut_meal.setSodium_wt(0.06);
        coconut_meal.setId(4);
        coconut_meal.setQuntity(1);
        coconut_meal.setImage(R.drawable.coconut_meal);
        coconut_meal.setEt("1.0");
        list.add(coconut_meal);


        //Dried Brewer's Grain
        Choose_diet_pojo dried_brewer = new Choose_diet_pojo();
        dried_brewer.setMintitle("Dried Brewer's");
        dried_brewer.setTitle("Dried Brewer's Grain");
        dried_brewer.setEnergy_weight(2744);
        dried_brewer.setFat_weight(9);
        dried_brewer.setFiber_wieght(8.5);
        dried_brewer.setProtien_weight(27);
        dried_brewer.setAvP_wt(0.55);
        dried_brewer.setCa_wt(0.14);
        dried_brewer.setLysine(0.8);
        dried_brewer.setMethionine_wt(0.51);
        dried_brewer.setSodium_wt(0.2);
        dried_brewer.setId(8);
        dried_brewer.setQuntity(1);
        dried_brewer.setImage(R.drawable.dried_brewers_grain);
        dried_brewer.setEt("1.0");
        list.add(dried_brewer);


        //Bone Meal
        Choose_diet_pojo bone_meal = new Choose_diet_pojo();
        bone_meal.setMintitle("Bone Meal");
        bone_meal.setTitle("Bone Meal");
        bone_meal.setEnergy_weight(0);
        bone_meal.setFat_weight(0);
        bone_meal.setFiber_wieght(0);
        bone_meal.setProtien_weight(0);
        bone_meal.setAvP_wt(12);
        bone_meal.setCa_wt(24);
        bone_meal.setLysine(0);
        bone_meal.setMethionine_wt(0);
        bone_meal.setSodium_wt(0.46);
        bone_meal.setId(0);
        bone_meal.setQuntity(1);
        bone_meal.setImage(R.drawable.bone_meal);
        bone_meal.setEt("1.0");
        list.add(bone_meal);


        //Limestone
        Choose_diet_pojo limestone = new Choose_diet_pojo();
        limestone.setMintitle("Limestone");
        limestone.setTitle("Limestone");
        limestone.setEnergy_weight(0);
        limestone.setFat_weight(0);
        limestone.setFiber_wieght(0);
        limestone.setProtien_weight(0);
        limestone.setAvP_wt(0);
        limestone.setCa_wt(38);
        limestone.setLysine(0);
        limestone.setMethionine_wt(0);
        limestone.setSodium_wt(0);
        limestone.setId(13);
        limestone.setQuntity(1);
        limestone.setImage(R.drawable.limestone);
        limestone.setEt("1.0");
        list.add(limestone);


        //Oyster Shell
        Choose_diet_pojo Oyster = new Choose_diet_pojo();
        Oyster.setTitle("Oyster Shell");
        Oyster.setMintitle("O.Shell");
        Oyster.setEnergy_weight(0.0);
        Oyster.setFat_weight(0.0);
        Oyster.setFiber_wieght(0.0);
        Oyster.setProtien_weight(0.0);
        Oyster.setAvP_wt(0.0);
        Oyster.setCa_wt(38.0);
        Oyster.setLysine(0.0);
        Oyster.setMethionine_wt(0.0);
        Oyster.setSodium_wt(0.04);
        Oyster.setId(18);
        Oyster.setQuntity(1);
        Oyster.setImage(R.drawable.oyster);
        Oyster.setEt("1.0");
        list.add(Oyster);


        //Salt
        Choose_diet_pojo Salt = new Choose_diet_pojo();
        Salt.setTitle("Salt");
        Salt.setMintitle("Salt");
        Salt.setEnergy_weight(0.0);
        Salt.setFat_weight(0.0);
        Salt.setFiber_wieght(0.0);
        Salt.setProtien_weight(0.0);
        Salt.setAvP_wt(0.0);
        Salt.setCa_wt(0.0);
        Salt.setLysine(0.0);
        Salt.setMethionine_wt(0.0);
        Salt.setSodium_wt(39.0);
        Salt.setId(23);
        Salt.setQuntity(1);
        Salt.setImage(R.drawable.salt);
        Salt.setEt("1.0");
        list.add(Salt);

        //DL-Methionine
        Choose_diet_pojo dl_methionine = new Choose_diet_pojo();
        dl_methionine.setMintitle("DL-Methionine");
        dl_methionine.setTitle("DL-Methionine");
        dl_methionine.setEnergy_weight(3680);
        dl_methionine.setFat_weight(0);
        dl_methionine.setFiber_wieght(0);
        dl_methionine.setProtien_weight(59);
        dl_methionine.setAvP_wt(0);
        dl_methionine.setCa_wt(0);
        dl_methionine.setLysine(0);
        dl_methionine.setMethionine_wt(98);
        dl_methionine.setSodium_wt(0);
        dl_methionine.setId(7);
        dl_methionine.setQuntity(1);
        dl_methionine.setImage(R.drawable.dl_methionine);
        dl_methionine.setEt("1.0");
        list.add(dl_methionine);

        //L-Lysine
        Choose_diet_pojo l_lysine = new Choose_diet_pojo();
        l_lysine.setMintitle("L-Lysine");
        l_lysine.setTitle("L-Lysine");
        l_lysine.setEnergy_weight(4600);
        l_lysine.setFat_weight(0);
        l_lysine.setFiber_wieght(0);
        l_lysine.setProtien_weight(94.4);
        l_lysine.setAvP_wt(0);
        l_lysine.setCa_wt(0);
        l_lysine.setLysine(78.8);
        l_lysine.setMethionine_wt(0);
        l_lysine.setSodium_wt(0);
        l_lysine.setId(14);
        l_lysine.setQuntity(24);
        l_lysine.setImage(R.drawable.l_lysine);
        l_lysine.setEt("1.0");
        list.add(l_lysine);


        //Mono-dicalcium Phos
        Choose_diet_pojo mono_dicalcium = new Choose_diet_pojo();
        mono_dicalcium.setMintitle("Mono-dicalcium");
        mono_dicalcium.setTitle("Mono-dicalcium Phos");
        mono_dicalcium.setEnergy_weight(0);
        mono_dicalcium.setFat_weight(0);
        mono_dicalcium.setFiber_wieght(0);
        mono_dicalcium.setProtien_weight(0);
        mono_dicalcium.setAvP_wt(21);
        mono_dicalcium.setCa_wt(16);
        mono_dicalcium.setLysine(0);
        mono_dicalcium.setMethionine_wt(0);
        mono_dicalcium.setSodium_wt(0.05);
        mono_dicalcium.setId(17);
        mono_dicalcium.setQuntity(1);
        mono_dicalcium.setImage(R.drawable.mono_dicalcium_phos);
        mono_dicalcium.setEt("1.0");
        list.add(mono_dicalcium);


        //Deflorinated Phos
        Choose_diet_pojo deflorinated = new Choose_diet_pojo();
        deflorinated.setMintitle("Deflorinated");
        deflorinated.setTitle("Deflorinated Phos");
        deflorinated.setEnergy_weight(0);
        deflorinated.setFat_weight(0);
        deflorinated.setFiber_wieght(0);
        deflorinated.setProtien_weight(0);
        deflorinated.setAvP_wt(18);
        deflorinated.setCa_wt(33);
        deflorinated.setLysine(0);
        deflorinated.setMethionine_wt(0);
        deflorinated.setSodium_wt(4.5);
        deflorinated.setId(6);
        deflorinated.setQuntity(1);
        deflorinated.setImage(R.drawable.deflorinated_phos);
        deflorinated.setEt("1.0");
        list.add(deflorinated);


        //Palm Oil
        Choose_diet_pojo Palm = new Choose_diet_pojo();
        Palm.setTitle("Palm Oil");
        Palm.setMintitle("P.Oil");
        Palm.setEnergy_weight(8000);
        Palm.setFat_weight(100);
        Palm.setFiber_wieght(0);
        Palm.setProtien_weight(0);
        Palm.setAvP_wt(0);
        Palm.setCa_wt(0);
        Palm.setLysine(0);
        Palm.setMethionine_wt(0);
        Palm.setSodium_wt(0);
        Palm.setId(20);
        Palm.setQuntity(1);
        Palm.setImage(R.drawable.palmoil);
        Palm.setEt("1.0");
        list.add(Palm);


        //Soybean Oil
        Choose_diet_pojo Soybean = new Choose_diet_pojo();
        Soybean.setTitle("Soybean Oil");
        Soybean.setMintitle("S.Oil");
        Soybean.setEnergy_weight(8800);
        Soybean.setFat_weight(95);
        Soybean.setFiber_wieght(0);
        Soybean.setProtien_weight(0);
        Soybean.setAvP_wt(0);
        Soybean.setCa_wt(0);
        Soybean.setLysine(0);
        Soybean.setMethionine_wt(0);
        Soybean.setSodium_wt(0);
        Soybean.setId(25);
        Soybean.setQuntity(1);
        Soybean.setImage(R.drawable.soyaoil);
        Soybean.setEt("1.0");
        list.add(Soybean);

        //Poultry Fat
        Choose_diet_pojo poultry_fat = new Choose_diet_pojo();
        poultry_fat.setMintitle("Poultry Fat");
        poultry_fat.setTitle("Poultry Fat");
        poultry_fat.setEnergy_weight(8800);
        poultry_fat.setFat_weight(100);
        poultry_fat.setFiber_wieght(0);
        poultry_fat.setProtien_weight(0);
        poultry_fat.setAvP_wt(0);
        poultry_fat.setCa_wt(0);
        poultry_fat.setLysine(0);
        poultry_fat.setMethionine_wt(0);
        poultry_fat.setSodium_wt(0);
        poultry_fat.setId(21);
        poultry_fat.setQuntity(1);
        poultry_fat.setImage(R.drawable.poultry_fat);
        poultry_fat.setEt("1.0");
        list.add(poultry_fat);


        //Broiler Concentrate (35)
        Choose_diet_pojo Broiler = new Choose_diet_pojo();
        Broiler.setTitle("Broiler Concentrate (35)");
        Broiler.setMintitle("Broiler.Cnt");
        Broiler.setEnergy_weight(2250);
        Broiler.setFat_weight(4.8);
        Broiler.setFiber_wieght(2);
        Broiler.setProtien_weight(43);
        Broiler.setAvP_wt(0.38);
        Broiler.setCa_wt(2);
        Broiler.setLysine(3);
        Broiler.setMethionine_wt(1.2);
        Broiler.setSodium_wt(0.45);
        Broiler.setId(1);
        Broiler.setQuntity(1);
        Broiler.setImage(R.drawable.broiler);
        Broiler.setEt("1.0");
        list.add(Broiler);


        //Champrix Layer Conc (5)
        Choose_diet_pojo Champrix = new Choose_diet_pojo();
        Champrix.setTitle("Champrix Layer Conc (5)");
        Champrix.setMintitle("Chmprix");
        Champrix.setEnergy_weight(1950);
        Champrix.setFat_weight(5);
        Champrix.setFiber_wieght(3);
        Champrix.setProtien_weight(30);
        Champrix.setAvP_wt(1.16);
        Champrix.setCa_wt(4);
        Champrix.setLysine(1.7);
        Champrix.setMethionine_wt(2.3);
        Champrix.setSodium_wt(0.7);
        Champrix.setQuntity(1);
        Champrix.setId(3);
        Champrix.setQuntity(1);
        Champrix.setImage(R.drawable.champrix);
        Champrix.setEt("1.0");
        list.add(Champrix);


        //Hendrix Layer Conc (5)
        Choose_diet_pojo Hendrix = new Choose_diet_pojo();
        Hendrix.setMintitle("Hendrix");
        Hendrix.setTitle("Hendrix Layer Conc(5)");
        Hendrix.setEnergy_weight(2280);
        Hendrix.setFat_weight(1.8);
        Hendrix.setFiber_wieght(4.5);
        Hendrix.setProtien_weight(30);
        Hendrix.setAvP_wt(0.7);
        Hendrix.setCa_wt(2.7);
        Hendrix.setLysine(2);
        Hendrix.setMethionine_wt(3);
        Hendrix.setSodium_wt(2.9);
        Hendrix.setId(12);
        Hendrix.setQuntity(1);
        Hendrix.setImage(R.drawable.hendrix);
        Hendrix.setEt("1.0");
        list.add(Hendrix);


        return list;
    }

}
