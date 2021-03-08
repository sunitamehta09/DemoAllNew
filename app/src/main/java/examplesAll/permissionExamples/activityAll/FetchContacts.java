package examplesAll.permissionExamples.activityAll;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityFetchContactsBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import callBacksAll.PermissionCallBack;
import callBacksAll.RecyclerViewCallBack;
import controllerAll.utilsAll.CheckPermissions;
import controllerAll.validations.PhoneValidations;
import examplesAll.permissionExamples.adapterAll.FetchContactAdapter;

public class FetchContacts extends CheckPermissions {
    private ArrayList<HashMap<String, Object>> data = new ArrayList<>();
    private String sort_filter_string1, sort_filter_string2, tele_country_code, tele_country_code_without_sign;
    private ActivityFetchContactsBinding viewBinding;
    private FetchContactAdapter fetchContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(FetchContacts.this, R.layout.activity_fetch_contacts);

        initView();
    }

    private void initView() {
        viewBinding.layoutToolbar.header.setBackgroundColor(ContextCompat.getColor(FetchContacts.this, R.color.colorGrey));
        viewBinding.layoutToolbar.titleTxt.setText("Show Contacts");

        tele_country_code = "+" + GetCountryZipCode();
        tele_country_code_without_sign = GetCountryZipCode();

        viewBinding.layoutToolbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewBinding.lnProgressbar.setVisibility(View.VISIBLE);
        checkPermission();
    }

    private String GetCountryZipCode() {
        String CountryID = "", NetworkID = "", CountryZipCode = "";
        TelephonyManager manager = (TelephonyManager) FetchContacts.this.getSystemService(Context.TELEPHONY_SERVICE);
        CountryID = manager.getSimCountryIso().toUpperCase();
        Log.e("CountryID", "- " + CountryID);

        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                Log.e("Countryzipcode", "- " + CountryZipCode);
                break;
            }
        }
        return CountryZipCode;
    }

    private void checkPermission() {
        requestAllPermissions(FetchContacts.this, Manifest.permission.READ_CONTACTS, getString(R.string.read_contacts), 1, new PermissionCallBack() {
            @Override
            public void getPermissionResults(Object... args) {
                String permissionType = (String) args[0];
                if (permissionType.equals(getString(R.string.read_contacts))) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getContactData();
                        }
                    }).start();
                }
            }
        });
    }

    private void getContactData() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        HashMap<String, Object> hmap = null;

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String Contact_person_name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String Contact_person_number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                int has_phone_number = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                String imageUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                if (has_phone_number > 0) {
                    if (Contact_person_name != null && !Contact_person_name.isEmpty() && Contact_person_number != null && !Contact_person_number.isEmpty()) {
                        hmap = new HashMap<>();
                        String phone = PhoneValidations.Companion.removeCharactersInPhoneNo(Contact_person_number.trim());
                        String phoneTxt = PhoneValidations.Companion.showPhoneNoWithCode(tele_country_code, phone);
                        hmap.put("name", Contact_person_name);
                        hmap.put("number", phoneTxt);
                        hmap.put("invited", "false");
                      /*  if (imageUri.isEmpty())
                            hmap.put("image", "");
                        else
                            hmap.put("image", imageUri);*/
                    }
                }
                data.add(hmap);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Collections.sort(data, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            sort_filter_string1 = o1.get("name").toString();
                            sort_filter_string2 = o2.get("name").toString();
                            return sort_filter_string1.compareToIgnoreCase(sort_filter_string2);
                        }
                    });

                    for (int i = 0; i < data.size(); i++) {
                        for (int j = i + 1; j < data.size(); j++) {
                            if (data.get(i).get("name").toString().equals(data.get(j).get("name").toString())) {
                                data.remove(j);
                                j--;
                            }
                        }
                    }

                    fetchContactAdapter = new FetchContactAdapter(FetchContacts.this, getString(R.string.type_fetch_contacts),
                            R.layout.layout_adapter_fetch_contacts, data, new RecyclerViewCallBack() {
                        @Override
                        public void recycleCallBack(Object... args) {
                            String pos = (String) args[1];
                            String phoneNumber = (String) args[2];
                            for (int i = 0; i < data.size(); i++) {
                                HashMap<String, Object> hmap = data.get(i);
                                if (hmap.get("number").equals(phoneNumber)) {
                                    hmap.put("invited", "true");
                                    data.set(i, hmap);
                                    fetchContactAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

                    viewBinding.rvFetchcontacts.setLayoutManager(new LinearLayoutManager(FetchContacts.this));
                    viewBinding.rvFetchcontacts.setAdapter(fetchContactAdapter);
                    viewBinding.lnProgressbar.setVisibility(View.GONE);
                }
            });
        }
    }
}
