package com.common.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.blueberry.activity.R;

/**
 * 相片适配器
 * @author GuiLin
 */
public class SelcctPhotoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<PhotoInfo> list=new ArrayList<PhotoInfo>();
	private ViewHolder viewHolder;
	private GridView gridView;
	private int width ;
	public SelcctPhotoAdapter(Context context,GridView gridView){
		mInflater = LayoutInflater.from(context);
		this.gridView = gridView;
	}
	public SelcctPhotoAdapter(Context context,List<PhotoInfo> list,GridView gridView,int width){
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.width=width;
		this.gridView = gridView;
	}

	public void setList(List<PhotoInfo> list) {
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	/**
	 * 刷新view
	 * @param index
	 */
/*	public void refreshView(int index){
		int visiblePos = gridView.getFirstVisiblePosition();
		View view = gridView.getChildAt(index-visiblePos);
		ViewHolder holder = (ViewHolder)view.getTag();

		if(list.get(index).isChoose()){
			holder.selectImage.setImageResource(R.drawable.gou_selected);
		}else{
			holder.selectImage.setImageResource(R.drawable.gou_normal);
		}
	}*/

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_selectphotos, null);
			viewHolder.image = (ImageView)convertView.findViewById(R.id.imageView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		LayoutParams layoutParams = viewHolder.image.getLayoutParams();
		layoutParams.height = width;
		layoutParams.width=width;
		viewHolder.image.setLayoutParams(layoutParams);
		
	
		if (list.get(position).getBitmap()!=null) {
			viewHolder.image.setImageBitmap(list.get(position).getBitmap());
		}else {
			final PhotoInfo photoInfo = list.get(position);
			if(photoInfo!=null){
				UniversalImageLoadTool.disPlay(ThumbnailsUtil.MapgetHashValue(photoInfo.getImage_id(),photoInfo.getPath_file()), 
						new RotateImageViewAware(viewHolder.image,photoInfo.getPath_absolute()), R.drawable.zw174);
//				UniversalImageLoadTool.disPlay(ThumbnailsUtil.MapgetHashValue(photoInfo.getImage_id(),photoInfo.getPath_file()),
//						viewHolder.image, R.drawable.common_defalt_bg);
			}
		}
		System.out.println(width+"--------------------");
		
		return convertView;
	}
	public class ViewHolder{
		public ImageView image;
		public ImageView delete;
	}
}
