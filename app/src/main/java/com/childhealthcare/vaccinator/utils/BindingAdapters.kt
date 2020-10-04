package com.childhealthcare.vaccinator.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.ui.GenericRecyclerViewAdapter
import com.childhealthcare.vaccinator.ui.OnItemViewClickListener
import com.childhealthcare.vaccinator.ui.OnListItemClickListener
import de.hdodenhof.circleimageview.CircleImageView

//@BindingAdapter("imageTint")
//fun setImage(view: ImageView, colorResource: Int) {
//    view.setColorFilter(
//        ContextCompat.getColor
//            (view.context, colorResource), android.graphics.PorterDuff.Mode.SRC_IN
//    )
//}

@BindingAdapter("imgResource")
fun setImage(view: ImageView, imgResource: Int) {
    view.setImageResource(imgResource)
}

@BindingAdapter(value = ["imageUrl", "default"], requireAll = false)
fun loadImage(view: ImageView, imageUrl: String?, default: Drawable?) {
    if (default == null) {
        Glide.with(view.context).load(imageUrl).into(view)
    } else {
        Glide.with(view.context).load(imageUrl).placeholder(default).into(view)
    }
}

@BindingAdapter("imageUri")
fun loadImage(view: ImageView, imageUrl: Uri?) {
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter(value = ["imageUrl", "default", "borderColor"], requireAll = false)
fun loadImage(
    view: CircleImageView,
    imageUrl: String?,
    default: Drawable?,
    borderColor: Int = Color.WHITE
) {
    if (default == null) {
        Glide.with(view.context).load(imageUrl).into(view)
    } else {
        Glide.with(view.context).load(imageUrl).placeholder(default).into(view)
    }
    view.borderColor = borderColor
}

@BindingAdapter("drawableLeft")
fun setDrawableLeft(view: TextView, drawableLeft: Int) {
    val img = view.context.resources.getDrawable(drawableLeft)
    view.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
}

@BindingAdapter("html")
fun setHtml(view: TextView, html: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        view.text = Html.fromHtml(html)
    }
}

@BindingAdapter("error")
fun setError(view: EditText, error: String?) {
    if (!error.isNullOrEmpty())
        view.error = error
}

@BindingAdapter(value = ["nextEditText", "previousEditText"], requireAll = false)
fun gotoNextEditText(
    view: EditText,
    nextView: EditText? = null,
    previousEditText: EditText? = null
) {
    view.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.isNullOrEmpty()) {
                if (previousEditText == null)
                    return
                view.clearFocus()
                previousEditText.requestFocus()
                return
            }
            if (s.length >= 2) {
                view.setText(s.toString().substring(s.length - 1, s.length))
                view.setSelection(view.text.length)
            }
            if (nextView == null) return
            view.clearFocus()
            nextView.requestFocus()
        }

    })
}

@BindingAdapter(
    value = ["itemsList", "itemLayout", "itemClickListener", "hasFixSize", "onItemViewClick"],
    requireAll = false
)
fun <T> setItems(
    view: RecyclerView, itemsList: List<T>, layout: Int,
    itemClickListener: OnListItemClickListener<T>?, hasFixSize: Boolean = false,
    onItemViewClick: OnItemViewClickListener<T>?
) {
    val mAdapter = GenericRecyclerViewAdapter(itemsList, layout)
    view.adapter = mAdapter
    mAdapter.setItemClickListener(itemClickListener)
    mAdapter.onItemViewClick = onItemViewClick
    view.setHasFixedSize(hasFixSize)
}

@BindingAdapter("spItemsList")
fun <T> setSpinnerItems(view: Spinner, spItemsList: List<T>) {
    val adapter = ArrayAdapter(
        view.context,
        R.layout.item_spinner_header, R.id.text1, spItemsList
    )
    adapter.setDropDownViewResource(R.layout.item_spinner)
    view.adapter = adapter
}

@BindingAdapter("currentItem")
fun setCurrentItem(view: Spinner, currentItem: Int) {
    if (currentItem == -1) return
    view.setSelection(currentItem)
}

@BindingAdapter("selectedPos")
fun setSelectedItem(view: Spinner, selectedPos: Int) {
    view.setSelection(selectedPos)
}

@BindingAdapter("navigation")
fun navigateUp(view: ImageView, isEnabled: Boolean) {
    view.setOnClickListener {
        it.findNavController().navigateUp()
    }
}

@BindingAdapter("isSelected")
fun select(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}

//@BindingAdapter(value = ["isLoading", "newText"], requireAll = false)
//fun setLoading(view: Button, isLoading: Boolean, newText: String?) {
//    view.attachTextChangeAnimator()
//    if (isLoading) {
//        view.isEnabled = false
//        view.showProgress {
//            buttonText = "Please Wait..."
//            progressColor = view.currentTextColor
//        }
//    } else {
//        view.isEnabled = true
//        view.hideProgress(newText)
//    }
//}

@BindingAdapter("isRefreshing")
fun setRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
    view.isRefreshing = isRefreshing
}

//@BindingAdapter(value = ["webUrl", "webViewClient"], requireAll = false)
//fun loadWebUrl(view: WebView, webUrl: String? = null, webViewClient: WebViewClient? = WebViewClient()){
//    view.loadUrl(webUrl)
//    view.settings.javaScriptEnabled = true
//    view.webViewClient = webViewClient
//}

//@BindingAdapter(
//    value = ["listDataHeader", "listChildData", "headerLayout", "childLayout", "onChildClickListener"],
//    requireAll = false
//)
//fun <T, U> setListItems(
//    view: ExpandableListView,
//    listDataHeader: List<T>,
//    listChildData: HashMap<T, List<U>>,
//    headerLayout: Int,
//    childLayout: Int,
//    onChildClickListener: ExpandableListView.OnChildClickListener? = null
//) {
//    val adapter =
//        GenericExpandableListAdapter(headerLayout, childLayout, listDataHeader, listChildData)
//    view.setAdapter(adapter)
//    view.setOnChildClickListener(onChildClickListener)
//}
