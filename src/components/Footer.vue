<template>

        
        <footer id="footerpad">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-8 mx-auto">
                        <ul class="list-inline text-center">
                            <li class="list-inline-item"><a href="#"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-facebook fa-stack-1x fa-inverse"></i></span></a></li>
                            <li class="list-inline-item"><a href="#"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-twitter fa-stack-1x fa-inverse"></i></span></a></li>
                            <li class="list-inline-item"><a href="#"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-instagram fa-stack-1x fa-inverse"></i></span></a></li>
                            <li class="list-inline-item"><a href="#"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-pinterest fa-stack-1x fa-inverse"></i></span></a></li>
                        </ul>
                        <p class="copyright text-muted">Copyright &copy; KIRAYATY 2024 | Web Design by Designer</p>
                    </div>
                </div>
            </div>
        </footer>

</template>

<script>
export default {

           name: 'FooterAPP'
    
   
   
}
</script>



<style scoped>

/* Style du footer */
#footerpad {
    padding: 20px 0;
    text-align: center;
    background-color: #9AC8EB;
}

#footerpad .list-inline-item a {
    color: rgb(134, 31, 65); /* Couleur des icônes */
    transition: color 0.3s ease;
}

#footerpad .list-inline-item a:hover {
    color: #000; /* Changer la couleur au survol */
}
</style>
