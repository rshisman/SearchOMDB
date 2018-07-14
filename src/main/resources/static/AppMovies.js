Vue.use(uiv);

Vue.component('app-movies', {
    template: `
    <div class="app-container">
        <label for="search-movie" placeholder="Type to search...">Movies:</label>
        <div class="search-box row">
            <input id="search-movie" class="col-sm-6" type="text" placeholder="Type to search...">
            <button @click="getMoviesByTitle()"  tabindex="0">Search Now</button>
        </div>
        <typeahead v-model="movieModel" target="#search-movie" item-key="title" async-src="http://localhost:8080/api/movies/title/"/>    
        <br/>       
        <div class="movies-container">          
            <movie-overview v-for="currMovie in movies" v-if="isPresentableMovie(currMovie)" :key="currMovie.imdbID" :movie="currMovie"/>
        </div>
    </div>`,
    data: function () {
        return {
            movieModel: '',
            movies: []
        }
    },
    mounted() {

    },
    methods: {
        isPresentableMovie(movie) {
            return (movie.poster !== "N/A");
        },
        searchedTitle() {
            // typeahead works in the following way if selected the exact move found the object then need to tkae the tile from it otherwise this is merely a string
            let title = this.movieModel;
            if (this.isObject(this.movieModel)) {
                title = this.movieModel.title;
            }
            return title;
        },
        getMoviesByTitle() {
            let search = 'http://localhost:8080/api/movies/title/' + this.searchedTitle();
            axios.get(search)
                .then((response) => {
                    this.movies = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        isObject (value) {
            return value && typeof value === 'object';
        }
    }
});