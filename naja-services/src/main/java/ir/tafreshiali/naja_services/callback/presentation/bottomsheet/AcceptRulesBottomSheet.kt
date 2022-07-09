package ir.tafreshiali.naja_services.callback.presentation.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import ir.ayantech.ayannetworking.api.SimpleCallback
import ir.ayantech.pishkhancore.ui.bottomSheet.AyanBaseBottomSheet
import ir.ayantech.whygoogle.helper.setHtmlText
import ir.tafreshiali.naja_services.databinding.BottomSheetAcceptRulesBinding

class AcceptRulesBottomSheet(
    context: Context,
    private val rules: String,
    private val acceptText: String,
    private val callBack: SimpleCallback
) :
    AyanBaseBottomSheet<BottomSheetAcceptRulesBinding>(context) {
    override val binder: (LayoutInflater) -> BottomSheetAcceptRulesBinding
        get() = BottomSheetAcceptRulesBinding::inflate

    override fun onCreate() {
        super.onCreate()
        binding.acceptCb.setOnCheckedChangeListener { _, isChecked ->
            binding.acceptButton.isEnabled = isChecked
        }
        binding.rulesTv.setHtmlText(rules)
        binding.acceptButton.text = acceptText
        binding.acceptButton.setOnClickListener {
            dismiss()
            callBack()
        }
        binding.acceptTv.setOnClickListener {
            binding.acceptCb.performClick()
        }
    }
}