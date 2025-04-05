package com.camgist.scribbledash.feature_drawing.data.repository

import com.camgist.scribbledash.feature_drawing.domain.model.Path
import com.camgist.scribbledash.feature_drawing.domain.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * In-memory implementation of the DrawingRepository
 */
class InMemoryDrawingRepository : DrawingRepository {
    
    private val _paths = MutableStateFlow<List<Path>>(emptyList())
    
    override fun getPaths(): Flow<List<Path>> {
        return _paths.asStateFlow()
    }
    
    override suspend fun addPath(path: Path) {
        _paths.update { currentPaths ->
            currentPaths + path
        }
    }
    
    override suspend fun clearPaths() {
        _paths.update { emptyList() }
    }
    
    override suspend fun undoLastPath() {
        _paths.update { currentPaths ->
            if (currentPaths.isEmpty()) {
                emptyList()
            } else {
                currentPaths.dropLast(1)
            }
        }
    }
} 