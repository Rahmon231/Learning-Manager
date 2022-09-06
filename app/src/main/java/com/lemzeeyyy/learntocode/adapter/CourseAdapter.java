package com.lemzeeyyy.learntocode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lemzeeyyy.learntocode.CourseDiffCallback;
import com.lemzeeyyy.learntocode.R;
import com.lemzeeyyy.learntocode.databinding.CourseListItemBinding;
import com.lemzeeyyy.learntocode.model.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<Course> courseArrayList = new ArrayList<>();
    private onItemClickedListener listener;


    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CourseListItemBinding courseListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()),
                R.layout.course_list_item,
                parent,
                false);
        return new CourseViewHolder(courseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        Course course = courseArrayList.get(position);
        holder.courseListItemBinding.setCourse(course);
    }

    @Override
    public int getItemCount() {

        return null!=courseArrayList?courseArrayList.size():0;
//        if(courseArrayList != null){
//            return courseArrayList.size();
//        }else {
//            return 0;
//        }
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private CourseListItemBinding courseListItemBinding;

        public CourseViewHolder(@NonNull CourseListItemBinding courseListItemBinding) {
            super(courseListItemBinding.getRoot());
            this.courseListItemBinding = courseListItemBinding;
            courseListItemBinding.getRoot().setOnClickListener(view -> {
                int clickPosition = getAdapterPosition();
                if(clickPosition!=RecyclerView.NO_POSITION && listener!=null){
                    listener.onItemClicked(courseArrayList.get(clickPosition));
                }
            });
        }

    }
    public interface onItemClickedListener{
        void onItemClicked(Course course);
    }
    public void setListener(onItemClickedListener listener){
        this.listener = listener;
    }

//    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
//        this.courseArrayList = courseArrayList;
//        notifyDataSetChanged();
//    }
public void setCourseArrayList(ArrayList<Course> newCourseList) {
   final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new
           CourseDiffCallback(courseArrayList,newCourseList),false);
   courseArrayList = newCourseList;
   result.dispatchUpdatesTo(CourseAdapter.this);
}
}

