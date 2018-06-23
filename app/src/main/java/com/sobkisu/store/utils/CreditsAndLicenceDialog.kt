package com.sobkisu.store.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.marcoscg.licenser.Library
import com.marcoscg.licenser.License
import com.marcoscg.licenser.LicenserDialog
import com.sobkisu.store.R
import com.sobkisu.store.model.ImageCredits
import kotlinx.android.synthetic.main.activity_photo_credits.*


class CreditsAndLicenceDialog {


    fun initLicenceDialog(context: Context): LicenserDialog? {

        return LicenserDialog(context)
                .setTitle("Licenses And Agreements")
                .setCancelable(true)
                .setCustomNoticeTitle("Notices for Library: ")
                .setLibrary(Library("Android Support Libraries",
                        "https://developer.android.com/topic/libraries/support-library/index.html",
                        License.APACHE))
                .setLibrary(Library("Picasso",
                        "https://github.com/square/picasso",
                        License.APACHE))
                .setLibrary(Library("Example Library",
                        "https://github.com/marcoscgdev",
                        License.APACHE))
                .setLibrary(Library("ImagePicker",
                        "https://github.com/esafirm/android-image-picker",
                        License.APACHE))
                .setLibrary(Library("Material SearchBar Android Material Design Search Bar for Android ",
                        "https://github.com/mancj/MaterialSearchBar",
                        License.MIT))
                .setLibrary(Library("CircularImageView",
                        "https://github.com/lopspower/CircularImageView",
                        License.APACHE))
                .setLibrary(Library("Material Spinner",
                        "https://github.com/jaredrummler/MaterialSpinner",
                        License.APACHE))
                .setLibrary(Library("Licenser",
                        "https://github.com/marcoscgdev/Licenser",
                        License.MIT))


    }

    fun intiCreditsLialog(context: Context) {
        var dialog: Dialog = Dialog(context)
        dialog.setContentView(R.layout.activity_photo_credits)

        dialog.creditImageRecyclerView.layoutManager = LinearLayoutManager(context)
        dialog.creditImageRecyclerView.adapter = CreditImageAdapter(dialog.context, getImageCredits())

        dialog.show()
    }

    private fun getImageCredits(): ArrayList<ImageCredits> {
        val list = ArrayList<ImageCredits>()

        list.add(ImageCredits(authorName = "Freepik", authorLink = "https://www.freepik.com/", imageId = R.drawable.ic_discount))
        list.add(ImageCredits(authorName = "Roundicons", authorLink = "https://roundicons.com/", imageId = R.drawable.ic_add_trolley))
        list.add(ImageCredits(authorName = "Roundicons", authorLink = "https://roundicons.com/", imageId = R.drawable.ic_shopping_cart2))
        list.add(ImageCredits(authorName = "Prosymbols", authorLink = "https://www.flaticon.com/authors/prosymbols", imageId = R.drawable.ic_category_checklist))
        list.add(ImageCredits(authorName = "Freepik", authorLink = "https://www.freepik.com/", imageId = R.drawable.ic_trolley))
        list.add(ImageCredits(authorName = "Freepik", authorLink = "https://www.freepik.com/", imageId = R.drawable.ic_intersect_circle))

        return list

    }

}

class CreditImageAdapter(var context: Context, private var imageCredits: ArrayList<ImageCredits>) : RecyclerView.Adapter<CreditImageAdapter.CreditViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        return CreditViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_credit_item, parent, false))
    }

    override fun getItemCount(): Int {

        return imageCredits.size
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        try {
            holder.imageCredit.setBackgroundResource(imageCredits[position].imageId)
            holder.authorName.text = "Icon made by ${imageCredits.get(position).authorName} from"

            onclick(holder.authorName, position, imageCredits[position].authorLink)
        } catch (e: Exception) {
        }

    }

    private fun onclick(textView: TextView, position: Int, authorLink1: String) {
        textView.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(authorLink1)))
        }

    }

    class CreditViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var authorName = itemView!!.findViewById<TextView>(R.id.authorName)!!
        var imageCredit = itemView!!.findViewById<ImageView>(R.id.creditImage)!!
    }


}
