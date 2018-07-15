Vue.use(uiv);

Vue.component('app-movies', {
    template: `
    <div class="app-container">
        <div class="search-area container" >
            <h3>Search Movies</h3>
            <br/><br/>
            <div class="search-box row">
                <input id="search-movie" class="col-sm-6" type="text" placeholder="Type to search...">                 
                <button @click="getMoviesByTitle()"  tabindex="0">Search Now</button>
            </div>
            <div v-if="hasSearchResults">{{results()}}</div>      
            <typeahead v-model="movieModel" target="#search-movie" item-key="title" async-src="http://localhost:8080/api/movies/title/"/>              
        </div>          
        <div class="movies-container">          
            <movie-overview v-for="currMovie in movies" :key="currMovie.id" :movie="currMovie"/>
        </div>
    </div>`,
    data: function () {
        return {
            movieModel: '',
            lastSearchStr: '',
            hasSearchResults: false,
            movies: []
        }
    },
    methods: {
        results() {
            let strPrefix = "There are no results ";
            if (this.movies.length == 1) {
                strPrefix = "There is only one result ";
            }
            else if (this.movies.length > 1) {
                strPrefix = `There are ${this.movies.length} results `
            }
            return strPrefix + `upon searching for "${this.lastSearchStr}".`;
        },
        isPresentableMovie(movie) {
            return (movie.poster !== "N/A");
        },
        searchedStr() {
            // the 'typeahead' component works in the following way: if the user types something and clicks search - then the input model is a string.
            // If the user selects a value from the autocomplete, then the input model is a movie object.
            let title = this.movieModel;
            if (this.isObject(this.movieModel)) {
                title = this.movieModel.title;
            }
            return title;
        },
        getMoviesByTitle() {
            this.movies = [];
            let search = 'http://localhost:8080/api/movies/title/' + this.searchedStr();
            axios.get(search)
                .then((response) => {
                    this.hasSearchResults = true;
                    this.lastSearchStr = this.searchedStr();
                    // put in only movies which are valid (with a poster)
                    response.data.forEach((movie) => {
                        if (this.isPresentableMovie(movie)) {
                            this.movies.push(movie);
                        }
                    });
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