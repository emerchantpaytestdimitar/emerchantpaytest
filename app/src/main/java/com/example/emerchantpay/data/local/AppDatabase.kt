import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.RepositoryModel

@Database(entities = [RepositoryModel::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "emerchantpay_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
