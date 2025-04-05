package com.camgist.scribbledash.feature_drawing.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camgist.scribbledash.feature_drawing.data.repository.InMemoryDrawingRepository
import com.camgist.scribbledash.feature_drawing.domain.model.Path
import com.camgist.scribbledash.feature_drawing.domain.repository.DrawingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DrawingViewModel : ViewModel() {
    
    // Using in-memory repository for now
    private val repository: DrawingRepository = InMemoryDrawingRepository()
    
    // Current drawing configuration
    var currentColor by mutableStateOf(Color.Black)
        private set
    
    var currentStrokeWidth by mutableStateOf(5f)
        private set
    
    // Drawing paths
    val paths = repository.getPaths().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    // Functions for drawing operations
    fun addPath(path: Path) {
        viewModelScope.launch {
            repository.addPath(path)
        }
    }
    
    fun clearCanvas() {
        viewModelScope.launch {
            repository.clearPaths()
        }
    }
    
    fun undoLastPath() {
        viewModelScope.launch {
            repository.undoLastPath()
        }
    }
    
    fun setColor(color: Color) {
        currentColor = color
    }
    
    fun setStrokeWidth(width: Float) {
        currentStrokeWidth = width
    }
} 