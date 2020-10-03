package com.gyeong.practicesupportlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import static com.gyeong.practicesupportlibrary.PreferenceFragmentActivity.TARGET_SETTING_PAGE;

public class SettingPreferenceFragment2 extends PreferenceFragmentCompat {
    SharedPreferences prefs;
    Preference soundPrefer;
    Preference keywordSoundPrefer;
    Preference keywordScreen;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey);
        if(rootKey==null){
            soundPrefer=findPreference("sound_list");
            keywordSoundPrefer=findPreference("keyword_sound_list");
            keywordScreen=findPreference("keyword_screen");

            prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

            if(!prefs.getString("sound_list", "").equals("")){
                soundPrefer.setSummary(prefs.getString("sound_list", "카톡"));
            }
            if(!prefs.getString("keyword_sound_list","").equals("")){
                keywordSoundPrefer.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }
            if(!prefs.getBoolean("keyword", false)){
                keywordScreen.setSummary("사용");
            }
        }

    }

    //        설정 변경 이벤트 처리
    SharedPreferences.OnSharedPreferenceChangeListener prefListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if(s.equals("sound_list")){
                soundPrefer.setSummary(prefs.getString("sound_list", "카톡"));
            }
            if(s.equals("keyword_sound_list")){
                keywordSoundPrefer.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }
        }
    };

//        내부 PreferenceScreen에 의한 화면 ㅣㅇ동 처리

    @Override
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        Intent intent=new Intent(getActivity(), PreferenceFragmentActivity.class).putExtra(TARGET_SETTING_PAGE, preferenceScreen.getKey());
        startActivity(intent);
    }
}
