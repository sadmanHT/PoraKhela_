package com.porakhela.ui.subject;

/**
 * DiffUtil callback for efficient RecyclerView updates
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/porakhela/ui/subject/CategoryDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/porakhela/data/models/SubjectCategory;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
public final class CategoryDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.porakhela.data.models.SubjectCategory> {
    
    public CategoryDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SubjectCategory oldItem, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SubjectCategory newItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SubjectCategory oldItem, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SubjectCategory newItem) {
        return false;
    }
}