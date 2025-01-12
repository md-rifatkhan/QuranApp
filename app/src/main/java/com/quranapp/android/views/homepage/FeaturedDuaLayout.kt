/*
 * Copyright (c) Faisal Khan (https://github.com/faisalcodes)
 * Created on 28/2/2022.
 * All rights reserved.
 */
package com.quranapp.android.views.homepage

import android.content.Context
import android.util.AttributeSet
import com.peacedesign.android.utils.Dimen
import com.quranapp.android.R
import com.quranapp.android.adapters.ADPDua
import com.quranapp.android.components.quran.QuranDua
import com.quranapp.android.components.quran.QuranMeta
import com.quranapp.android.components.quran.dua.Dua
import com.quranapp.android.databinding.LytHomepageTitledItemTitleBinding
import com.quranapp.android.utils.extensions.color

class FeaturedDuaLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HomepageCollectionLayoutBase(context, attrs, defStyleAttr) {
    override fun getHeaderTitle(): Int {
        return R.string.strTitleFeaturedDuas
    }

    override fun getHeaderIcon(): Int {
        return R.drawable.dr_icon_rabbana
    }

    override fun showViewAllBtn(): Boolean {
        return true
    }

    override fun setupHeader(context: Context, header: LytHomepageTitledItemTitleBinding) {
        header.titleIcon.setColorFilter(context.color(R.color.colorPrimary))
    }

    override fun onViewAllClick(context: Context) {
//        context.startActivity(Intent(context, ActivityDua::class.java)) TODO
    }

    private fun refreshFeatured(ctx: Context, duas: List<Dua>) {
        hideLoader()

        val featured = duas.subList(0, duas.size.coerceAtMost(6))
        resolveListView().adapter = ADPDua(ctx, Dimen.dp2px(ctx, 200f), featured)
    }

    fun refresh(quranMeta: QuranMeta) {
        showLoader()

        QuranDua.prepareInstance(context, quranMeta) { duas ->
            refreshFeatured(context, duas)
        }
    }
}