package ir.tafreshiali.naja_services.presentation.bottomsheet


import android.content.Context
import android.view.LayoutInflater
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanBaseBottomSheet
import ir.ayantech.whygoogle.helper.StringCallBack
import ir.ayantech.whygoogle.helper.changeVisibility
import ir.tafreshiali.naja_services.R
import ir.tafreshiali.naja_services.databinding.BottomSheetOneInputBinding

class OneInputBottomSheet(
    context: Context,
    private val title: String,
    private val message: String?,
    private val hint: String,
    private val action: String,
    private val suggestions: List<String>?,
    private val stringCallBack: StringCallBack
) : AyanBaseBottomSheet<BottomSheetOneInputBinding>(context) {

    override val binder: (LayoutInflater) -> BottomSheetOneInputBinding
        get() = BottomSheetOneInputBinding::inflate

    override fun onCreate() {
        super.onCreate()
        binding.apply {
            titleTv.text = title
            inputEt.hint = hint
            actionBtn.text = action

            actionBtn.setOnClickListener {
                if (inputEt.text.toString().isEmpty()) {
                    inputEt.error = context.resources.getString(R.string.tv_Vin_error)
                    return@setOnClickListener
                }
                dismiss()
                stringCallBack(inputEt.text.toString())
            }
            if (suggestions != null) {
                suggestionBtn.text = suggestions.first()
                suggestionBtn.setOnClickListener {
                    inputEt.setText(suggestions.first())
                }
                suggestions.firstOrNull()?.isNotEmpty()?.let {
                    binding.guideTv.changeVisibility(it)
                    binding.suggestionBtn.changeVisibility(it)
                }
            } else {
                binding.guideTv.changeVisibility(false)
                binding.suggestionBtn.changeVisibility(false)
            }
            messageTv.changeVisibility(message != null)
            messageTv.text = message
        }
    }
}