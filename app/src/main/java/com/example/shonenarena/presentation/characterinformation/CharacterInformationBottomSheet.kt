package com.example.shonenarena.presentation.characterinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Move
import com.example.shonenarena.databinding.CharacterInformationBottomSheetBinding
import com.example.shonenarena.presentation.common.base.BaseBottomSheetDialog
import com.example.shonenarena.presentation.moves.adapter.MovesListAdapter

class CharacterInformationBottomSheet : BaseBottomSheetDialog() {
    private lateinit var binding: CharacterInformationBottomSheetBinding
    private lateinit var adapter: MovesListAdapter
    private val args: CharacterInformationBottomSheetArgs by navArgs()
    override val viewModel: CharacterInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterInformationBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        with(binding) {
            adapter = MovesListAdapter {
                viewModel.navigateToTargets(args.characterInformation.enemyList)
            }
            adapter.moveList =
                args.characterInformation.moveList.map {
                    Move(it.id, it.name, it.imageUrl, it.description)
                }
            movesRecyclerView.adapter = adapter
            movesRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}