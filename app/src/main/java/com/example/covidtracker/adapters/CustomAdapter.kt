
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.models.DataModel


class CustomAdapter(context: Context,private val dataAPI: List<DataModel>) :    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stateNameTextView: TextView
        val recoveredCasesTextView: TextView
        val activeCasesTextView : TextView
        val deathsTextView :TextView

        init {
            // Define click listener for the ViewHolder's View.
            stateNameTextView = view.findViewById(R.id.list_state_name)
            recoveredCasesTextView = view.findViewById(R.id.list_item_recovered)
            activeCasesTextView= view.findViewById(R.id.list_item_activecases)
            deathsTextView = view.findViewById(R.id.list_item_deaths)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.stateNameTextView.text = dataAPI[position].getStateName()
        viewHolder.recoveredCasesTextView.text = dataAPI[position].getRecovered().toString()
        viewHolder.deathsTextView.text = dataAPI[position].getDeaths().toString()
        viewHolder.activeCasesTextView.text = dataAPI[position].getActivecases().toString()  // Get element from your dataset at this position and replace the
        // contents of the view with that element
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataAPI.size

}

