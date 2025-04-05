package com.reactnativephotoeditor.activity.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reactnativephotoeditor.R;

import java.util.ArrayList;
import java.util.List;

public class EditingToolsAdapter extends RecyclerView.Adapter<EditingToolsAdapter.ViewHolder> {

  private final List<ToolModel> mToolList = new ArrayList<>();
  private final OnItemSelected mOnItemSelected;

  public EditingToolsAdapter(Context context, OnItemSelected onItemSelected) {
    mOnItemSelected = onItemSelected;

    mToolList.add(new ToolModel(context.getString(R.string.label_shape), R.drawable.ic_brush, ToolType.SHAPE));
    mToolList.add(new ToolModel(context.getString(R.string.label_eraser), R.drawable.ic_eraser, ToolType.ERASER));
    mToolList.add(new ToolModel(context.getString(R.string.label_filter), R.drawable.ic_colorfilter, ToolType.FILTER));
    mToolList.add(new ToolModel(context.getString(R.string.label_sticker), R.drawable.ic_sticker, ToolType.STICKER));
    mToolList.add(new ToolModel(context.getString(R.string.label_text), R.drawable.ic_smallcaps, ToolType.TEXT));
  }

  public interface OnItemSelected {
    void onToolSelected(ToolType toolType);
  }

  static class ToolModel {
    final String mToolName;
    final int mToolIcon;
    final ToolType mToolType;

    ToolModel(String toolName, int toolIcon, ToolType toolType) {
      mToolName = toolName;
      mToolIcon = toolIcon;
      mToolType = toolType;
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.row_editing_tools, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ToolModel item = mToolList.get(position);
    holder.txtTool.setText(item.mToolName);
    holder.imgToolIcon.setImageResource(item.mToolIcon);
  }

  @Override
  public int getItemCount() {
    return mToolList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final ImageView imgToolIcon;
    final TextView txtTool;

    ViewHolder(View itemView) {
      super(itemView);
      imgToolIcon = itemView.findViewById(R.id.imgToolIcon);
      txtTool = itemView.findViewById(R.id.txtTool);
      itemView.setOnClickListener(v ->
        mOnItemSelected.onToolSelected(mToolList.get(getLayoutPosition()).mToolType)
      );
    }
  }
}
