Vue.use(uiv);

Vue.component('app-movies', {
    template: `
    <div class="app-container">
        <label for="search-movie" placeholder="Type to search...">Movies:</label>
        <input id="search-movie" class="form-control" type="text" placeholder="Type to search...">
        <button @click="getMoviesByTitle()">Search Now</button>    
        <typeahead v-model="movieModel" target="#search-movie" item-key="title" :async-function="queryFunction"/>    
        <br/>     
        <!--alert v-show="movieModel">You selected {{movieModel}}</alert-->        
        <div class="movies-container">          
            <movie-overview v-for="currMovie in movies" :key="currMovie.imdbID" :movie="currMovie"/>
        </div>
    </div>`,
    data: function () {
        return {
            movieModel: '',
            movies: []
        }
    },
    computed: {
    },
    methods: {
        searchURL() {
            // typeahead works in the following way if selected the exact move found the object then need to tkae the tile from it otherwise this is merely a string
            let title = this.movieModel;
            if (this.isObject(this.movieModel)) {
                title = this.movieModel.title;
            }
            return 'http://localhost:8080/api/movies?title=' + title.toLowerCase();
        },
        getMoviesByTitle() {
            let search = this.searchURL();
            axios.get(search)
                .then((response) => {
                    this.movies = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        queryFunction (query, done) {
            let search = this.searchURL();
            axios.get(search)
                .then(response => {
                    done(response.data)
                })
                .catch(err => {
                    // any error handler
                })
        },
        isObject (value) {
            return value && typeof value === 'object';
        }
    }
});