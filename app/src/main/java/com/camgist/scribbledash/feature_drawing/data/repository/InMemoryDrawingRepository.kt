package com.camgist.scribbledash.feature_drawing.data.repository

import com.camgist.scribbledash.feature_drawing.domain.model.Path
import com.camgist.scribbledash.feature_drawing.domain.repository.DrawingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/**
 * In-memory implementation of the DrawingRepository
 */
class InMemoryDrawingRepository : DrawingRepository {
    
    // All paths on the canvas (no limit)
    private val _paths = MutableStateFlow<List<Path>>(emptyList())
    
    // Paths that can be undone (limited to maxUndoHistory)
    private val _undoablePaths = MutableStateFlow<List<Path>>(emptyList())
    
    // Paths that have been undone and can be redone
    private val _undonePaths = MutableStateFlow<List<Path>>(emptyList())
    
    // Max number of paths to keep in undo/redo history
    private val maxUndoHistory = 5
    
    override fun getPaths(): Flow<List<Path>> {
        return _paths.asStateFlow()
    }
    
    override suspend fun addPath(path: Path) {
        // Add the new path to the current paths without any limit
        _paths.update { currentPaths ->
            currentPaths + path
        }
        
        // Add the path to undoable paths with limit
        _undoablePaths.update { currentUndoablePaths ->
            // Limit the undoable paths to maxUndoHistory (5)
            if (currentUndoablePaths.size >= maxUndoHistory) {
                currentUndoablePaths.drop(1) + path
            } else {
                currentUndoablePaths + path
            }
        }
        
        // Clear the undone paths when a new path is drawn
        _undonePaths.update { emptyList() }
    }
    
    override suspend fun clearPaths() {
        _paths.update { emptyList() }
        _undoablePaths.update { emptyList() }
        _undonePaths.update { emptyList() }
    }
    
    override suspend fun undoLastPath() {
        // Only allow undo if there are undoable paths
        if (_undoablePaths.value.isNotEmpty()) {
            // Get the last undoable path
            val lastUndoablePath = _undoablePaths.value.last()
            
            // Remove it from undoable paths
            _undoablePaths.update { it.dropLast(1) }
            
            // Add it to undone paths (for redo) with limit
            _undonePaths.update { currentUndonePaths ->
                // Limit the undone paths to maxUndoHistory (5)
                if (currentUndonePaths.size >= maxUndoHistory) {
                    currentUndonePaths.drop(1) + lastUndoablePath
                } else {
                    currentUndonePaths + lastUndoablePath
                }
            }
            
            // Remove it from all paths (visible on canvas)
            _paths.update { currentPaths ->
                currentPaths.filterNot { it == lastUndoablePath }
            }
        }
    }
    
    override suspend fun redoLastPath() {
        // Only allow redo if there are undone paths
        if (_undonePaths.value.isNotEmpty()) {
            // Get the last undone path
            val lastUndonePath = _undonePaths.value.last()
            
            // Remove it from undone paths
            _undonePaths.update { it.dropLast(1) }
            
            // Add it back to undoable paths with limit
            _undoablePaths.update { currentUndoablePaths ->
                // Limit the undoable paths to maxUndoHistory (5)
                if (currentUndoablePaths.size >= maxUndoHistory) {
                    currentUndoablePaths.drop(1) + lastUndonePath
                } else {
                    currentUndoablePaths + lastUndonePath
                }
            }
            
            // Add it back to all paths (visible on canvas)
            _paths.update { currentPaths -> 
                currentPaths + lastUndonePath
            }
        }
    }
    
    override fun hasUndonePathsFlow(): Flow<Boolean> {
        return _undonePaths.map { it.isNotEmpty() }
    }
    
    // Helper to check if there are paths that can be undone
    override fun hasUndoablePathsFlow(): Flow<Boolean> {
        return _undoablePaths.map { it.isNotEmpty() }
    }
} 