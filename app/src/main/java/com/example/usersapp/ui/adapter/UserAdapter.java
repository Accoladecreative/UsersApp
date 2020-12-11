package com.example.usersapp.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usersapp.R;
import com.example.usersapp.data.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter <UserAdapter.UserHolder> {
    public List<User> users = new ArrayList<>();
    public OnItemClickListener listener;

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new UserHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.id.setText(String.valueOf(currentUser.getId()));
        holder.name.setText(currentUser.getFirstName() + " "+ currentUser.getLastName());
        holder.email.setText(currentUser.getEmail());
        holder.phone.setText(currentUser.getPhone());

            //Toast.makeText(context, "Book Update Successfully",Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(UpdateBook.this, MainActivity.class);
                startActivity(intent);*/

    }
//todo: get the  items

    @Override
    public int getItemCount() {
        return users.size();
    }
    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();

    }
    public User getUserAt(int position){
        return users.get(position);
    }

    class UserHolder extends RecyclerView.ViewHolder{

        TextView name, email, phone, id;


        public UserHolder(@NonNull View itemView) {
            super(itemView);
            id =(TextView)itemView.findViewById(R.id.UserId);
            name = (TextView)itemView.findViewById(R.id.name);
            email = (TextView)itemView.findViewById(R.id.email);
            phone = (TextView)itemView.findViewById(R.id.phone);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position!= RecyclerView.NO_POSITION){
                listener.onItemClick(users.get(position));
                }
            });
        }
    }

   public interface OnItemClickListener{
        void onItemClick(User user);

    }
    public void  SetOnClickListener(OnItemClickListener listener){
        this.listener= listener;
    }
}
