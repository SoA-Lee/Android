package layout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val items = ArrayList<Fragment>()
    //position 위치의 프레그먼트
    override fun getItem(position: Int): Fragment {
        return items[position]

    }
    //아이템 개수
    override fun getCount(): Int {
        return items.size
    }
    //아이템 갱신
    fun updateFragments(items:List<Fragment>){
        this.items.addAll(items)
    }

}