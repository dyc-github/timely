package timely.com.timely.helpers

import android.content.Context
import com.squareup.phrase.Phrase
import javax.inject.Inject

class StringProviderImpl @Inject constructor(private val context: Context) : StringProvider {
    override fun getPhrase(resource: Int, values: Map<String, String>): String {
        val phrase = Phrase.from(context.getString(resource))
        for (value in values) {
            phrase.put(value.key, value.value)
        }
        return phrase.format().toString()
    }
}

interface StringProvider {
    fun getPhrase(resource: Int, values: Map<String, String>): String
}