package com.example.user.poiskovichok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter1 extends BaseExpandableListAdapter {

    private final Context mContext;
    private final List<String> listDataHeader;
    private final HashMap<String, List<String>> listDataChild;
    private final List<Integer> groupImages;
    private final HashMap<Integer, List<Integer>> childImages;

    public ExpandableListAdapter1(Context mContext, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, List<Integer> groupImages, HashMap<Integer,List<Integer>> childImages) {
        this.mContext = mContext;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
        this.groupImages = groupImages;
        this.childImages = childImages;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        try{
            return listDataChild.get(listDataHeader.get(groupPosition)).size();
        }catch (NullPointerException e){

            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header_title = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item, null);
        }

        TextView groupHeader =  convertView.findViewById(R.id.group);
        ImageView imageView = convertView.findViewById(R.id.group_image);
        ImageView imageViewArrow = convertView.findViewById(R.id.group_arrow);
        groupHeader.setText(header_title);

        View separator = convertView.findViewById(R.id.separator);
        separator.setVisibility(View.GONE);

        if(header_title.equals("Текущие мероприятия")){
            separator.setVisibility(View.VISIBLE);
        }

        if(header_title.equals("Связаться с разработчиком")){
            separator.setVisibility(View.VISIBLE);
        }

        int imageId = this.groupImages.get(groupPosition);
        imageView.setImageResource(imageId);


        if (getChildrenCount(groupPosition) == 0) {
            imageViewArrow.setVisibility(View.INVISIBLE);
        } else {
            imageViewArrow.setVisibility(View.VISIBLE);
            if(isExpanded){
                imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
            }else{
                imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            }
        }



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String child_title = String.valueOf(getChild(groupPosition, childPosition));
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }

        TextView childHeader = convertView.findViewById(R.id.child);
        ImageView imageView = convertView.findViewById(R.id.child_image);
        childHeader.setText(child_title);
        int imageId = this.childImages.get(this.groupImages.get(groupPosition)).get(childPosition);
        imageView.setImageResource(imageId);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
