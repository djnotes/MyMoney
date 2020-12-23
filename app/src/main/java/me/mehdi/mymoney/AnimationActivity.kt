package me.mehdi.mymoney

import androidx.appcompat.app.AppCompatActivity

class AnimationActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onBackPressed() {
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom)
        super.onBackPressed()
    }
}