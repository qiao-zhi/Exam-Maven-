/**
 * Created by yorge on 2017/9/15.
 */

/**lixianyuan  start***/
 $(document).ready(function () {

        new jPlayerPlaylist({
            jPlayer: "#jquery_jplayer_1",
            cssSelectorAncestor: "#jp_container_1"
        }, [
            {
               /* 列表标题*/
                title: "Big Buck Bunny Trailer",
                //视频制作人
                artist: "Blender Foundation",
                //显示视频类型
                free: true,
                //视频地址，下边是不同类型的集中视频
                mp4: "s/aaa.mp4",
                //ogv: "http://www.jplayer.org/video/ogv/Big_Buck_Bunny_Trailer.ogv",
                //webmv: "http://www.jplayer.org/video/webm/Big_Buck_Bunny_Trailer.webm",
                poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
            },
            {
                title: "Finding Nemo Teaser",
                artist: "Pixar",
                m4v: "http://www.jplayer.org/video/m4v/Finding_Nemo_Teaser.m4v",
                ogv: "http://www.jplayer.org/video/ogv/Finding_Nemo_Teaser.ogv",
                webmv: "http://www.jplayer.org/video/webm/Finding_Nemo_Teaser.webm",
                poster: "http://www.jplayer.org/video/poster/Finding_Nemo_Teaser_640x352.png"
            },
            {
                title: "Incredibles Teaser",
                artist: "Pixar",
                free:true,
                m4v: "http://www.jplayer.org/video/m4v/Incredibles_Teaser.m4v",
                ogv: "http://www.jplayer.org/video/ogv/Incredibles_Teaser.ogv",
                webmv: "http://www.jplayer.org/video/webm/Incredibles_Teaser.webm",
                poster: "http://www.jplayer.org/video/poster/Incredibles_Teaser_640x272.png"
            }
        ], {
            swfPath: "js/jplayer",
            supplied: "webmv, ogv, m4v, mp4",
            useStateClassSkin: true,
            autoBlur: false,
            smoothPlayBar: true,
            keyEnabled: true
        });

        new jPlayerPlaylist({
            jPlayer: "#jquery_jplayer_2",
            cssSelectorAncestor: "#jp_container_2"
        }, [
            {
                title: "Cro Magnon Man",
                mp3: "http://www.jplayer.org/audio/mp3/TSP-01-Cro_magnon_man.mp3",
                oga: "http://www.jplayer.org/audio/ogg/TSP-01-Cro_magnon_man.ogg"
            },
            {
                title: "Your Face",
                mp3: "http://www.jplayer.org/audio/mp3/TSP-05-Your_face.mp3",
                oga: "http://www.jplayer.org/audio/ogg/TSP-05-Your_face.ogg"
            }
        ], {
            swfPath: "js/jplayer",
            supplied: "oga, mp3",
            wmode: "window",
            useStateClassSkin: true,
            autoBlur: false,
            smoothPlayBar: true,
            keyEnabled: true
        });
    });
 
/**lixianyuan   end**/    