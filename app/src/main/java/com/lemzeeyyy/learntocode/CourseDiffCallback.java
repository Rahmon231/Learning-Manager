package com.lemzeeyyy.learntocode;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.lemzeeyyy.learntocode.model.Course;

import java.util.ArrayList;

public class CourseDiffCallback extends DiffUtil.Callback {
    ArrayList<Course> oldCourseList;
    ArrayList<Course> newCourseList;

    public CourseDiffCallback(ArrayList<Course> oldCourseList, ArrayList<Course> newCourseList) {
        this.oldCourseList = oldCourseList;
        this.newCourseList = newCourseList;
    }

    @Override
    public int getOldListSize() {
        return oldCourseList == null ? 0 : oldCourseList.size();
    }

    @Override
    public int getNewListSize() {
        return newCourseList == null ? 0 : newCourseList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCourseList.get(oldItemPosition).getCourse_id() ==
                newCourseList.get(newItemPosition).getCourse_id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCourseList.get(oldItemPosition).equals(newCourseList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
