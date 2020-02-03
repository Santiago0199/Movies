package com.santiagoperdomo.movies.movies

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import javax.inject.Inject
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.movies.R
import com.santiagoperdomo.movies.root.Const
import com.santiagoperdomo.movies.root.MyApp

class MoviesActivity : AppCompatActivity(), MoviesMVP.View {

    @Inject
    lateinit var presenter: MoviesMVP.Presenter

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MoviesAdapter
    lateinit var listMovies: ArrayList<Movie>
    lateinit var progress: SweetAlertDialog
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApp).component.inject(this)

        showProgress()
        initView()
        onClickList()
        refresh()
    }

    private fun showProgress(){
        progress = SweetAlertDialog(this)
        progress.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        progress.setTitle("Cargando...")
        progress.setCancelable(false)
        progress.progressHelper.barColor = Color.parseColor("#883997")
        progress.show()
    }

    private fun initView(){
        refresh = findViewById(R.id.refresh)
        refresh.setColorSchemeColors(Color.parseColor("#ba68c8"))
        recyclerView = findViewById(R.id.recyclerMovies)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(false)
        listMovies = ArrayList()
        adapter = MoviesAdapter(listMovies)
        recyclerView.adapter = adapter
    }

    private fun onClickList(){
        adapter.initOnClickListener(View.OnClickListener {
            val dialogDetail = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(Const.MOVIE, listMovies[recyclerView.getChildAdapterPosition(it)])
            dialogDetail.arguments = bundle
            dialogDetail.show(supportFragmentManager, "")
        })
    }

    private fun refresh(){
        refresh.setOnRefreshListener {
            val counter = object : CountDownTimer(3000,1000){
                override fun onFinish() {
                    presenter.unsuscribe()
                    listMovies.clear()
                    presenter.loadData()
                    refresh.isRefreshing = false
                }
                override fun onTick(p0: Long) {}
            }
            counter.start()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsuscribe()
        listMovies.clear()
        adapter.notifyDataSetChanged()
    }

    override fun updateData(movie: Movie) {
        listMovies.add(movie)
        adapter.notifyDataSetChanged()
    }

    override fun successRequest() {
        progress.dismiss()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}