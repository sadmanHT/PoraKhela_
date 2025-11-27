package com.porakhela.ui.subject;

/**
 * Fragment displaying categories for a specific subject
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J&\u0010!\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u001a\u0010(\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010)\u001a\u00020\u0016H\u0002J\b\u0010*\u001a\u00020\u0016H\u0002J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\bH\u0002J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006."}, d2 = {"Lcom/porakhela/ui/subject/SubjectCategoryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "categoryAdapter", "Lcom/porakhela/ui/subject/CategoryAdapter;", "rvCategories", "Landroidx/recyclerview/widget/RecyclerView;", "subject", "", "getSubject", "()Ljava/lang/String;", "subject$delegate", "Lkotlin/Lazy;", "tvSubjectDescription", "Landroid/widget/TextView;", "tvSubjectTitle", "viewModel", "Lcom/porakhela/ui/subject/SubjectCategoryViewModel;", "getViewModel", "()Lcom/porakhela/ui/subject/SubjectCategoryViewModel;", "viewModel$delegate", "displaySubjectData", "", "subjectData", "Lcom/porakhela/data/models/SubjectData;", "initializeViews", "view", "Landroid/view/View;", "loadSubjectData", "navigateToLessons", "category", "Lcom/porakhela/data/models/SubjectCategory;", "onCategoryClicked", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "setupObservers", "setupRecyclerView", "showError", "message", "showLockedCategoryMessage", "app_debug"})
public final class SubjectCategoryFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy subject$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private android.widget.TextView tvSubjectTitle;
    private android.widget.TextView tvSubjectDescription;
    private androidx.recyclerview.widget.RecyclerView rvCategories;
    private com.porakhela.ui.subject.CategoryAdapter categoryAdapter;
    
    public SubjectCategoryFragment() {
        super();
    }
    
    private final java.lang.String getSubject() {
        return null;
    }
    
    private final com.porakhela.ui.subject.SubjectCategoryViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews(android.view.View view) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupObservers() {
    }
    
    private final void loadSubjectData() {
    }
    
    private final void displaySubjectData(com.porakhela.data.models.SubjectData subjectData) {
    }
    
    private final void onCategoryClicked(com.porakhela.data.models.SubjectCategory category) {
    }
    
    private final void showLockedCategoryMessage(com.porakhela.data.models.SubjectCategory category) {
    }
    
    private final void navigateToLessons(com.porakhela.data.models.SubjectCategory category) {
    }
    
    private final void showError(java.lang.String message) {
    }
}