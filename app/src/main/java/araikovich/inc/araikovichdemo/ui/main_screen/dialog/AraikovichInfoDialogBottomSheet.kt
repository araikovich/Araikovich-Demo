package araikovich.inc.araikovichdemo.ui.main_screen.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import araikovich.inc.araikovichdemo.R
import araikovich.inc.araikovichdemo.ui.utils.loadCircleIconRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_araikovich_info_dialog.*

class AraikovichInfoDialogBottomSheet : BottomSheetDialogFragment() {

    private var bottomSheet: View? = null
    private var bottomSheetBehavior: BottomSheetBehavior<View?>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        bottomSheetDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        bottomSheetDialog.setOnShowListener {
            val dialog = it as BottomSheetDialog
            bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)?.apply {
                val params = this.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(0, 0, 0, 0)
                params.height = ViewGroup.MarginLayoutParams.MATCH_PARENT
                requestLayout()
            }
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
                isHideable = true
                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(p0: View, p1: Float) {}

                    override fun onStateChanged(p0: View, state: Int) {
                        if (state == BottomSheetBehavior.STATE_HIDDEN) {
                            dismiss()
                        }
                    }
                })
            }
        }
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.bottom_sheet_araikovich_info_dialog, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivAraikovich.loadCircleIconRes(R.drawable.my_photo)
    }
}