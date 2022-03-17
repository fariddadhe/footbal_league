package com.farid.proyekfootballeague.data.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.data.source.remote.RemoteRepository
import com.farid.proyekfootballeague.data.source.remote.response.*


open class LigaRepository(
    private val remoteRepository: RemoteRepository
) : LigaDataSource {
    override fun getDetailLeague(id: String): LiveData<List<LeaguesDetail>> {
        val result = MutableLiveData<List<LeaguesDetail>>()

        remoteRepository.getLeagueDetail(id, object : RemoteRepository.LoadLeagueDetailReceived {
            override fun onLeagueDetailReceived(leaguesDetailResponse: List<LeaguesDetail>) {
                result.postValue(leaguesDetailResponse)
            }

            override fun onDataNotAvailable() {
            }
        })
        return result
    }

    override fun getLeagues(): LiveData<List<Leagues>> {
        val result = MutableLiveData<List<Leagues>>()

        remoteRepository.getLeagues(object : RemoteRepository.LoadLeagueReceived {
            override fun onLeagueReceived(leagues: List<Leagues>?) {
                result.postValue(leagues)
            }

            override fun onDataNotAvailable() {
            }

        })
        return result
    }

    override fun getNextMatch(id: String): LiveData<List<EventEntity>> {
        val result = MutableLiveData<List<EventEntity>>()

        remoteRepository.getNextMatch(id, object : RemoteRepository.LoadNextMatchReceived {
            override fun onNextMatchReceived(match: List<EventEntity>) {
                result.postValue(match)
            }

            override fun onDataNotAvailable() {
            }

        })
        return result
    }

    override fun getPreviousMatch(id: String): LiveData<List<EventEntity>> {
        val result = MutableLiveData<List<EventEntity>>()

        remoteRepository.getPreviousMatch(id, object : RemoteRepository.LoadPreviousMatchReceived {
            override fun onPreviousMatchReceived(match: List<EventEntity>) {
                result.postValue(match)
            }

            override fun onDataNotAvailable() {
            }
        })

        return result
    }

    override fun getDetailMatch(id: String): LiveData<List<EventEntity>> {
        val result = MutableLiveData<List<EventEntity>>()

        remoteRepository.getDetailMatch(id, object : RemoteRepository.LoadDetailMatchReceived {
            override fun onDetailMatchReceived(match: List<EventEntity>) {
                result.postValue(match)
            }

            override fun onDataNotAvailable() {
            }

        })

        return result
    }

    override fun getTeams(id: String): LiveData<List<TeamsEntity>> {
        val result = MutableLiveData<List<TeamsEntity>>()

        remoteRepository.getTeams(id, object : RemoteRepository.LoadTeamsReceived {
            override fun onTeamsReceived(teams: List<TeamsEntity>) {
                result.postValue(teams)
            }

            override fun onDataNotAvailable() {
            }

        })

        return result
    }

    override fun getAllTeamLeague(id: String): LiveData<List<TeamsEntity>> {
        val result = MutableLiveData<List<TeamsEntity>>()

        remoteRepository.getAllTeamLeague(id, object : RemoteRepository.LoadAllTeamLeagueReceived {
            override fun onAllTeamLeagueReceived(team: List<TeamsEntity>) {
                result.postValue(team)
            }

            override fun onDataNotAvailable() {

            }

        })

        return result
    }

    override fun getTableLeague(id: String): LiveData<List<TableEntity>> {
        val result = MutableLiveData<List<TableEntity>>()

        remoteRepository.getTableLeague(id, object : RemoteRepository.LoadTableLeagueReceived {
            override fun onTableLeagueReceived(table: List<TableEntity>) {
                result.postValue(table)
            }

            override fun onDataNotAvailable() {
            }

        })

        return result
    }

    override fun getSearchMatch(query: String): LiveData<List<EventEntity>> {
        val result = MutableLiveData<List<EventEntity>>()

        remoteRepository.getSearchMatch(query, object : RemoteRepository.LoadSearchMatchReceived {
            override fun onSearchReceived(match: List<EventEntity>) {
                result.postValue(match)
            }

            override fun onDataNotAvailable(match: List<EventEntity>) {
                result.postValue(match)
            }

        })

        return result
    }

    override fun getSearchTeam(t: String): LiveData<List<TeamsEntity>> {
        val result = MutableLiveData<List<TeamsEntity>>()

        remoteRepository.getSearchTeam(t, object : RemoteRepository.LoadSearchTeamReceived {
            override fun onSearchTeamReceived(team: List<TeamsEntity>) {
                result.postValue(team)
            }

            override fun onDataNotAvailable(team: List<TeamsEntity>) {
                result.postValue(team)
            }

        })

        return result
    }

    override fun getFavoriteStateTeam(table: String, id: String, context: Context): Boolean {
        var result = true

        remoteRepository.getFavoriteStateTeam(
            table,
            id,
            context,
            object : RemoteRepository.LoadFavoriteState {
                override fun onDataReceived(boolean: Boolean) {
                    result = boolean
                }

                override fun onDataNotAvailable(boolean: Boolean) {
                    result = boolean
                }

            })

        return result
    }

    override fun getFavoriteState(table: String, id: String, context: Context): Boolean {
        var result = true

        remoteRepository.getFavoriteState(
            table,
            id,
            context,
            object : RemoteRepository.LoadFavoriteState {
                override fun onDataReceived(boolean: Boolean) {
                    result = boolean
                }

                override fun onDataNotAvailable(boolean: Boolean) {
                    result = boolean
                }

            })

        return result
    }

    override fun addFavoriteTeam(table: String, team: TeamsEntity, context: Context): String {
        var result = ""

        remoteRepository.addFavoriteTeam(
            table,
            team,
            context,
            object : RemoteRepository.AddToFavorite {
                override fun onAddSuccess(message: String) {
                    result = message
                }

                override fun onAddFailed(message: String) {
                    result = message
                }

            })

        return result
    }

    override fun addFavorite(table: String, list: List<EventEntity>, context: Context): String {
        var result = ""

        remoteRepository.addFavorite(table, list, context, object : RemoteRepository.AddToFavorite {
            override fun onAddSuccess(message: String) {
                result = message
            }

            override fun onAddFailed(message: String) {
                result = message
            }

        })

        return result
    }

    override fun removeFavoriteTeam(table: String, id: String, context: Context): String {
        var result = ""

        remoteRepository.removeFavoriteTeam(
            table,
            id,
            context,
            object : RemoteRepository.RemoveFavorite {
                override fun onRemoveSuccess(message: String) {
                    result = message
                }

                override fun onRemoveFailed(message: String) {
                    result = message
                }

            })
        return result
    }

    override fun removeFavorite(table: String, id: String, context: Context): String {
        var result = ""

        remoteRepository.removeFavorite(
            table,
            id,
            context,
            object : RemoteRepository.RemoveFavorite {
                override fun onRemoveSuccess(message: String) {
                    result = message
                }

                override fun onRemoveFailed(message: String) {
                    result = message
                }

            })

        return result
    }

    override fun favoriteTeam(table: String, context: Context): LiveData<List<TeamsEntity>> {
        val result = MutableLiveData<List<TeamsEntity>>()

        remoteRepository.favoriteTeam(
            table,
            context,
            object : RemoteRepository.LoadFavoriteTeamReceived {
                override fun onDataReceived(team: List<TeamsEntity>) {
                    result.postValue(team)
                }

                override fun onDataNotAvailable(team: List<TeamsEntity>) {
                    result.postValue(team)
                }

            })

        return result
    }

    override fun favoriteMatch(
        table: String,
        event: String,
        context: Context
    ): LiveData<List<FavoriteMatch>> {
        val result = MutableLiveData<List<FavoriteMatch>>()

        remoteRepository.favoriteMatch(
            table,
            event,
            context,
            object : RemoteRepository.LoadFavoriteMatchReceived {
                override fun onDataReceived(match: List<FavoriteMatch>) {
                    result.postValue(match)
                }

                override fun onDataNotAvailable(match: List<FavoriteMatch>) {
                    result.postValue(match)
                }
            })

        return result
    }

    companion object {
        private var INSTANCE: LigaRepository? = null

        @JvmStatic
        fun getInstance(remoteRepository: RemoteRepository) =
            INSTANCE ?: synchronized(LigaRepository::class.java) {
                INSTANCE ?: LigaRepository(remoteRepository)
                    .also { INSTANCE = it }
            }

    }
}